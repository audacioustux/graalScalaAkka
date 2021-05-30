import org.graalvm.polyglot.*
import java.io.IOException
import scala.util.Using

object Polyglot {
  val engine = Engine.create()

  final def jsModuleEval() = {
    val jsSrc =
      "import { testHello } from '/root/Bytes/hello-scala/src/main/js/testModule.mjs'; testHello()"

    Using.resource(
      Context.newBuilder().engine(engine).allowAllAccess(true).build()
    ) { ctx =>
      val poly_exec_timer: Timer =
        new Timer("js module eval", Timer.TimeUnit.ns)
      ctx.getBindings("js").putMember("timer", poly_exec_timer)
      ctx.eval(Source.newBuilder("js", jsSrc, "testModule.mjs").build())
    }
  }
}
