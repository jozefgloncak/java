package gloncak.jozef.jsp.integratespringjsp2.service;

import gloncak.jozef.jsp.integratespringjsp2.api.service.MathematicalService;
import org.springframework.stereotype.Service;

@Service
public class MathematicalServiceImpl implements MathematicalService {

    @Override
    public boolean isPrime(int number) {
        int divisorCandidate = 2;
        while (divisorCandidate <= Math.sqrt(number)) {
            if (number % divisorCandidate == 0) {
                return false;
            }
            divisorCandidate++;
        }
        return true;
    }
}
