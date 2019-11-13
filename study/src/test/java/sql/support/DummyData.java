package sql.support;

import sql.dto.YearsCodingResponse;

import java.util.Arrays;
import java.util.List;

public class DummyData {
    private static final YearsCodingResponse ENGINEERING_MANAGER = new YearsCodingResponse("Engineering manager", "10.2");
    private static final YearsCodingResponse DEV_OPS_SPECIALIST = new YearsCodingResponse("DevOps specialist", "8.0");
    private static final YearsCodingResponse DESKTOP_OR_ENTERPRISE_APPLICATIONS_DEVELOPER = new YearsCodingResponse("Desktop or enterprise applications developer", "7.7");
    private static final YearsCodingResponse EMBEDDED_APPLICATIONS_OR_DEVICES_DEVELOPER = new YearsCodingResponse("Embedded applications or devices developer", "7.5");
    private static final YearsCodingResponse DATA_OR_BUSINESS_ANALYST = new YearsCodingResponse("Data or business analyst", "7.2");
    private static final YearsCodingResponse SYSTEM_ADMINISTRATOR = new YearsCodingResponse("System administrator", "7.0");
    private static final YearsCodingResponse DATABASE_ADMINISTRATOR = new YearsCodingResponse("Database administrator", "6.9");
    private static final YearsCodingResponse YEARS_CODING_RESPONSE = new YearsCodingResponse("Full-stack developer", "6.3");
    private static final YearsCodingResponse BACK_END_DEVELOPER = new YearsCodingResponse("Back-end developer", "6.2");
    private static final YearsCodingResponse EDUCATOR_OR_ACADEMIC_RESEARCHER = new YearsCodingResponse("Educator or academic researcher", "6.2");
    private static final YearsCodingResponse DESIGNER = new YearsCodingResponse("Designer", "6.0");
    private static final YearsCodingResponse QA_OR_TEST_DEVELOPER = new YearsCodingResponse("QA or test developer", "5.8");
    private static final YearsCodingResponse FRONT_END_DEVELOPER = new YearsCodingResponse("Front-end developer", "5.5");
    private static final YearsCodingResponse DATA_SCIENTIST_OR_MACHINE_LEARNING_SPECIALIST = new YearsCodingResponse("Data scientist or machine learning specialist", "5.5");
    private static final YearsCodingResponse MOBILE_DEVELOPER = new YearsCodingResponse("Mobile developer", "5.2");
    private static final YearsCodingResponse GAME_OR_GRAPHICS_DEVELOPER = new YearsCodingResponse("Game or graphics developer", "4.6");

    public static final List<YearsCodingResponse> YEARS_CODING_RESPONSES = Arrays.asList(
            ENGINEERING_MANAGER, DEV_OPS_SPECIALIST, DESKTOP_OR_ENTERPRISE_APPLICATIONS_DEVELOPER,
            EMBEDDED_APPLICATIONS_OR_DEVICES_DEVELOPER, DATA_OR_BUSINESS_ANALYST, SYSTEM_ADMINISTRATOR,
            DATABASE_ADMINISTRATOR, YEARS_CODING_RESPONSE, BACK_END_DEVELOPER,
            EDUCATOR_OR_ACADEMIC_RESEARCHER, DESIGNER, QA_OR_TEST_DEVELOPER,
            FRONT_END_DEVELOPER, DATA_SCIENTIST_OR_MACHINE_LEARNING_SPECIALIST, MOBILE_DEVELOPER,
            GAME_OR_GRAPHICS_DEVELOPER
    );
}
