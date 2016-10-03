package dotty.tools.dotc
package transform

import util.Positions._
import TreeTransforms.{MiniPhaseTransform, TransformerInfo}
import core._
import Contexts.Context, Constants._, StdNames._, Decorators._
import ast.Trees._

class BasicLinter extends MiniPhaseTransform {
  import ast.tpd._
  val phaseName = "basicLinter"
}
