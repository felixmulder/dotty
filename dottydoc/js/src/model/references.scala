package dotty.tools
package dottydoc
package js
package model

import scala.scalajs.{ js => sjs }
import sjs.annotation.ScalaJSDefined

/** Type References */
@ScalaJSDefined
sealed trait Reference extends sjs.Object {
  val kind: String
}

@ScalaJSDefined
trait TypeReference extends Reference {
  val title: String
  val tpeLink: MaterializableLink
  val paramLinks: sjs.Array[MaterializableLink]
}

@ScalaJSDefined
trait OrTypeReference extends Reference {
  val left: Reference
  val right: Reference
}

@ScalaJSDefined
trait AndTypeReference extends Reference {
  val left: Reference
  val right: Reference
}

@ScalaJSDefined
trait NamedReference extends Reference {
  val title: String
  val ref: Reference
  val isByName: Boolean
}

@ScalaJSDefined
trait ConstantReference extends Reference {
  val title: String
}

/** Materializable links */
@ScalaJSDefined
sealed trait MaterializableLink extends sjs.Object {
  val kind: String
  val title: String
}

@ScalaJSDefined
trait UnsetLink extends MaterializableLink {
  val query: String
}

@ScalaJSDefined
trait MaterializedLink extends MaterializableLink {
  val target: String
}

@ScalaJSDefined
trait NoLink extends MaterializableLink {
  val target: String
}