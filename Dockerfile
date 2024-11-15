FROM gradle:8.7.0-jdk21

WORKDIR /app

# Копируем все файлы проекта в рабочую директорию
COPY . /app

# Устанавливаем переменную окружения для активации профиля
ENV SPRING_PROFILES_ACTIVE=stage

# Собираем проект
RUN gradle installDist

# Запускаем приложение
CMD ["/app/build/install/app/bin/app"]