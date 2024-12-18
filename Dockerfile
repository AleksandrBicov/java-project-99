FROM gradle:8.7.0-jdk21

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем все файлы проекта в рабочую директорию
COPY . .

# Запускаем команду для сборки и установки приложения
RUN gradle installDist

# Запускаем приложение с указанием профиля
CMD ["java", "-cp", "/app/build/install/app/lib/*:/app/build/install/app/bin", "hexlet.code.AppApplication", "--spring.profiles.active=stage"]