package org.design.patterns.traits.composition.self_types

import org.design.patterns.traits.common.Notifier

trait AlarmNotifier {
  this: Notifier =>

  def trigger(): String

}
