[&#8593;](../README.md)


# About
Demonstrate usage of profiles in springframework.

# Usage
* in first context (test context) is profile activate through __getEnvironment().setActiveProfiles()__ method call,
* in second context (production context) is profile activate through setting system property __spring.profiles.active__.