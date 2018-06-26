package org.design.patterns.components.base

import org.design.patterns.components.model.Food

trait Cooker {

  def cook(what: String): Food

}
