package gloncak.jozef.java8.features.lambdas.api;

public interface TransformationBi {

    /**
     * Transforms {@code a} and {@code b} input parameters (somehow) to output value
     *
     * @param a
     * @param b
     * @return transformed value from a and b
     */
    String transform(String a, String b);
}
