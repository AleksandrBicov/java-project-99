clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew installBootDist

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

.PHONY: build frontend