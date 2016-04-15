package dotty.tools.dottydoc
package util

object Traversing {
  import model.Entities._

  def mutateEntities(e: Entity)(trans: Entity => Unit): Unit = e match {
    case e: Entity with Members =>
      trans(e)
      e.members.map(mutateEntities(_)(trans))
    case e: Entity => trans(e)
  }
}