setup:
	npm install
	./gradlew wrapper --gradle-version 8.7
	./gradlew build

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew installBootDist

inst:
	./gradlew build installDist

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	./gradlew jacocoTestReport




.PHONY: build frontend