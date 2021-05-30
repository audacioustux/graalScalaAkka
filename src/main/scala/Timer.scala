import Timer.TimeUnit
import org.graalvm.polyglot.HostAccess

object Timer {
  enum TimeUnit:
    case ns, us

  def current(unit: TimeUnit) = unit match {
    case TimeUnit.ns => System.nanoTime()
    case TimeUnit.us => System.currentTimeMillis()
  }
}
class Timer(label: String, unit: TimeUnit) {
  val start = Timer.current(unit)

  private def timerInitTime() =
    val timer = Timer(label, unit);
    timer.stop(log = false) - timer.start

  def stop(log: Boolean = true): Long = 
    val curr = Timer.current(unit)
    if (log) System.out.println(String.format("%s : %s±%s·%s", label, curr - start, timerInitTime(), unit))
    curr
}
