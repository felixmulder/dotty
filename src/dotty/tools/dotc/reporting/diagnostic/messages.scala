package dotty.tools
package dotc
package reporting
package diagnostic

import dotc.core._
import Contexts.Context, Decorators._, Symbols._, Names._
import util.{SourceFile, NoSource}
import util.{SourcePosition, NoSourcePosition}
import config.Settings.Setting
import interfaces.Diagnostic.{ERROR, WARNING, INFO}
import dotc.printing.SyntaxHighlighting._

object messages {

  /** Concrete messages to be consumed by the reporter ---------------------- */
  class Error(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String,
    explanation: String = ""
  ) extends MessageContainer(msgFn, pos, ERROR, kind, explanation)

  class Warning(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String,
    explanation: String = ""
  ) extends MessageContainer(msgFn, pos, WARNING, kind, explanation)

  class Info(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String,
    explanation: String = ""
  ) extends MessageContainer(msgFn, pos, INFO, kind, explanation)

  abstract class ConditionalWarning(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String,
    explanation: String = ""
  ) extends Warning(msgFn, pos, kind, explanation) {
    def enablingOption(implicit ctx: Context): Setting[Boolean]
  }

  class FeatureWarning(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String = "Feature Warning",
    explanation: String = ""
  ) extends ConditionalWarning(msgFn, pos, kind, explanation) {
    def enablingOption(implicit ctx: Context) = ctx.settings.feature
  }

  class UncheckedWarning(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String = "Unchecked Warning",
    explanation: String = ""
  ) extends ConditionalWarning(msgFn, pos, kind, explanation) {
    def enablingOption(implicit ctx: Context) = ctx.settings.unchecked
  }

  class DeprecationWarning(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String = "Deprecation Warning",
    explanation: String = ""
  ) extends ConditionalWarning(msgFn, pos, kind, explanation) {
    def enablingOption(implicit ctx: Context) = ctx.settings.deprecation
  }

  class MigrationWarning(
    msgFn: => Message,
    pos: SourcePosition,
    kind: String = "Migration Warning",
    explanation: String = ""
  ) extends ConditionalWarning(msgFn, pos, kind, explanation) {
    def enablingOption(implicit ctx: Context) = ctx.settings.migration
  }

  /**  Messages ----------------------------------------------------------------
    *
    *  The role of messages is to provide the necessary details for a simple to
    *  understand diagnostic event. Each message can be turned into a message
    *  container (one of the above) by calling the appropriate method on them.
    *  For instance:
    *
    *  ```scala
    *  EmptyCatchBlock(tree).error   // res: Error
    *  EmptyCatchBlock(tree).warning // res: Warning
    *  ```
    */
  import dotc.ast.Trees._
  import dotc.ast.untpd

  /** Syntax Errors --------------------------------------------------------- */
  abstract class EmptyCatchOrFinallyBlock(tryBody: untpd.Tree, errNo: String)(implicit ctx: Context)
  extends Message(errNo) {
     val explanation = {
       val tryString = tryBody match {
         case Block(Nil, untpd.EmptyTree) => "{}"
         case _ => tryBody.show
       }

       val code1 =
         s"""|try $tryString catch {
             |  case t: Throwable => ???
             |}""".stripMargin

       val code2 =
         s"""|try $tryString finally {
             |  // perform your cleanup here!
             |}""".stripMargin

       hl"""|A ${"try"} expression should be followed by some mechanism to handle any exceptions
            |thrown. Typically a ${"catch"} expression follows the ${"try"} and pattern matches
            |on any expected exceptions. For example:
            |
            |$code1
            |
            |It is also possible to follow a ${"try"} immediately by a ${"finally"} - letting the
            |exception propagate - but still allowing for some clean up in ${"finally"}:
            |
            |$code2""".stripMargin
     }
   }

   class EmptyCatchBlock(tryBody: untpd.Tree)(implicit ctx: Context)
   extends EmptyCatchOrFinallyBlock(tryBody, "E001") {
     val kind = "Syntax"
     val msg =
       hl"""|The ${"catch"} block does not contain a valid expression, try
            |adding a case like - `${"case e: Exception =>"}` to the block""".stripMargin
   }

   case class EmptyCatchAndFinallyBlock(tryBody: untpd.Tree)(implicit ctx: Context)
   extends EmptyCatchOrFinallyBlock(tryBody, "E002") {
     val kind = "Syntax"
     val msg =
       hl"""|A ${"try"} without ${"catch"} or ${"finally"} is equivalent to putting
            |its body in a block; no exceptions are handled.""".stripMargin
   }

  /** Type Errors ----------------------------------------------------------- */
  class DuplicateBind(bind: untpd.Bind, tree: untpd.CaseDef)(implicit ctx: Context)
  extends Message("E003") {
    val kind = "Naming"
    val msg = em"duplicate pattern variable: `${bind.name}`"

    val explanation = {
      val pat = tree.pat.show
      val guard = tree.guard match {
        case untpd.EmptyTree => ""
        case guard => s"if ${guard.show}"
      }

      val body = tree.body match {
        case Block(Nil, untpd.EmptyTree) => ""
        case body => s" ${body.show}"
      }

      val caseDef = s"case $pat$guard => $body"

      hl"""|For each ${"case"} bound variable names  have to be unique. In:
           |
           |$caseDef
           |
           |`${bind.name}` is not unique. Rename one of the bound variables!""".stripMargin
    }
  }

  class MissingIdent(tree: untpd.Ident, treeKind: String, name: Name)(implicit ctx: Context)
  extends Message("E004") {
    val kind = "Missing identifier"
    val msg = em"not found: $treeKind$name"

    val explanation = {
      hl"""|An identifier for `${name.show}` is missing. This means that something
           |has either been misspelt or you're forgetting an import""".stripMargin
    }
  }
}