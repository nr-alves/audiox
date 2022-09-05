//package com.factory13.audiox.ui;
//
//import com.factory13.audiox.Runner;
//import com.factory13.audiox.service.AudioService;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.annotation.Bean;
//
//@SpringBootApplication
//public class DemoSwingApplication {
//
//    /**
//     * Application main() method.
//     * <p>
//     * Uses the fluent {@link SpringApplicationBuilder} to create and run the
//     * {@link org.springframework.boot.SpringApplication} object.
//     * <p>
//     * The options specified:
//     *
//     * <ul>
//     * <li>headless(false) - allow AWT classes to be instantiated</li>
//     * <li>web(false) - prevents the bundling of Tomcat or other Web components
//     * </ul>
//     * <p>
//     * Execution is picked up by the {@link Runner} class, which implements
//     * {@link org.springframework.boot.CommandLineRunner}.
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(DemoSwingApplication.class)
//                .headless(false)
//                .web(WebApplicationType.NONE)
//                .run(args);
//    }
//
//    /**
//     * Creates the {@link DemoFrame} object and returns it.
//     * <p>
//     * This @Bean could have been replaced by a @Component annotation being
//     * added to the {@link DemoFrame} class.
//     *
//     * @return the application window
//     */
//    @Bean
//    public DemoFrame frame(AudioService audioService) {
//        return new DemoFrame(audioService);
//    }
//}
