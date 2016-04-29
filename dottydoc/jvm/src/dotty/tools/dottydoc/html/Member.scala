package dotty.tools.dottydoc
package html

import scalatags.Text.all._
import scalatags.Text.TypedTag
import org.scalajs.dom
import org.scalajs.dom.html.{Anchor, Div}

object MemberLayout {
  import model.Entities._
  import CustomTags._

  def member(m: Entity, parent: Entity) = {
    def toggleBetween(short: Div, and: Div): Unit =
      if (and.style.display == "none") {
        and.style.display = "block"
        short.style.display = "none"
      } else {
        and.style.display = "none"
        short.style.display = "block"
      }

    m match {
      case m: Entity with Modifiers =>
        val shortComment = div(
          cls := "mdl-cell mdl-cell--12-col summary-comment",
          raw(m.comment.fold("")(_.short))
        )

        val fullComment = div(
          cls := "mdl-cell mdl-cell--12-col full-comment",
          style := "display: none;",
          raw(m.comment.fold("")(_.body))
        )


        val hasLongerFullComment = m.comment.fold(false) { c =>
          c.short.length + 5 < c.body.length
        }

        val divs = div(
          cls :=
            s"""
            mdl-cell mdl-cell--12-col member
            ${if (hasLongerFullComment) "member-fullcomment" else ""}
            """,
          //onclick := { () => toggleBetween(shortComment, and = fullComment) }, //FIXME!
          div(
            cls := "mdl-cell mdl-cell--12-col",
            span(cls := "member-name", m.modifiers.mkString(" ") + " " + m.kind + " " + m.name),
            returnValue(m, parent)
          ),
          shortComment,
          fullComment
        )
        Seq(divs)
      case x => Seq(h1("ERROR: " + x.name))
    }
  }

  def returnValue(m: Entity with Modifiers, parent: Entity) = {
    // shortens: "Option.this.A" => "A"
    def shorten(s: String): String = s.split('.').toList match {
      case x :: Nil => x
      case x :: xs if x == parent.name => xs.last
      case xs => s
    }

    m match {
      case v: Val => span(cls := "return-value", ": " + shorten(v.returnValue))
      case d: Def => span(cls := "return-value", ": " + shorten(d.returnValue))
      case _ => span()
    }
  }
}