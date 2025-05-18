package mipt.guchievmb.hw1.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {
  private int counter = 0;

  @Before("execution(* mipt.guchievmb.hw1.controller..*(..))")
  public void logBeforeControllers(JoinPoint jp) {
    System.out.println("Вызов контроллера: " + jp.getSignature().getName());
    counter++;
  }

  @Around("execution(* mipt.guchievmb.hw1.controller..*(..))")
  public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
    Instant start = Instant.now();
    Object result = pjp.proceed();
    Instant end = Instant.now();
    System.out.println("Метод " + pjp.getSignature().getName() +
            " выполнен за " +
            Duration.between(start, end).toMillis() + " мс");
    counter++;
    return result;
  }

  public int getCounter() {
    return counter;
  }
}

