package dotty.tools
package dottydoc

import dotty.tools.dotc.{Compiler, CompilationUnit, Driver}
import dotty.tools.dotc.core.Phases.Phase
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.typer.FrontEnd
import dotty.tools.dotc.ast.Trees._

/** Custom Compiler with phases for the documentation tool
 *
 *  The idea here is to structure `dottydoc` around the new infrastructure. As
 *  such, dottydoc will itself be a compiler. It will, however, produce a format
 *  that can be used by other tools or web-browsers.
 *
 *  Example:
 *    1. Use the existing FrontEnd to typecheck the code being fed to dottydoc
 *    2. Create JSON from the results of the FrontEnd phase
 *    3. Split the JSON to chunks that are fed to Angular.JS
 */
case object DottyDocCompiler extends Compiler {
  override def phases: List[List[Phase]] =
    List(new FrontEnd)  ::
    List(new JsonPhase) ::
    Nil
}

class JsonPhase extends Phase {
  import model.Entities._
  def phaseName = "JsonPhase"

  def collect(tree: Tree[_]): Entity = tree match {
    case PackageDef(pid, st) => Package(pid.name.toString, st.map(collect))
    case cls @ TypeDef(name, rhs) if cls.isClassDef => Class(name.toString)
  }

  def run(implicit ctx: Context): Unit = {
    println("run called")
    val tree = collect(ctx.compilationUnit.tpdTree)
    println(tree)
  }

  override def runOn(units: List[CompilationUnit])(implicit ctx: Context): List[CompilationUnit] = {
    println("runOn called!")
    super.runOn(units)
  }
}

object DottyDoc extends Driver {
  override def newCompiler(implicit ctx: Context): Compiler = DottyDocCompiler

  override def main(args: Array[String]): Unit =
    super.main(args)
}
