package org.design.patterns.traits.basic

import org.design.patterns.traits.common.Notifier

class NotifierImpl(val notificationMessage: String) extends Notifier {

  override def clear(): Unit =
    System.out.println("cleared")

}
