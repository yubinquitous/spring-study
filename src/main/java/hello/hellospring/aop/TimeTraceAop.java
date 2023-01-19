package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP 적용에 필요
@Component  // 로 스프링 빈으로 등록해주어도 되지만, SpringConfig에 등록하여 사용하는 것도 좋음.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")    // AOP를 어디에 적용해줄지 타겟을 지정한다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + timeMs + "ms");
        }
    }
}
