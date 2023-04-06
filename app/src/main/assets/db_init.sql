PRAGMA foreign_keys = ON;

CREATE TABLE "workouts" (
  "id"			        INTEGER PRIMARY KEY,
  "workout_name"        TEXT NOT NULL,
  "image_background" 	TEXT NOT NULL
);

CREATE TABLE "exercises" (
  "id"			INTEGER PRIMARY KEY,
  "name"	    TEXT NOT NULL,
  "animation"   TEXT NOT NULL,
  "count"     INTEGER,
  "is_count_seconds"     INTEGER NOT NUll

);

CREATE TABLE "workouts_exercises" (
  "workout_id"		INTEGER NOT NULL,
  "exercise_id"		INTEGER NOT NULL
);


INSERT INTO "workouts" ("workout_name", "image_background")
VALUES
  ("Руки", "@drawable/download"),
  ("Пресс", "@drawable/download"),
  ("Ноги", "@drawable/download"),
  ("Плечи и спина", "@drawable/download"),
  ("Грудь", "@drawable/download");

INSERT INTO "exercises" ("name", "animation", "count", "is_count_seconds")
VALUES
   ("Прыжки", "add_later", 30, 0),
   ("Упражнение для пресса", "add_later", 16, 1),
   ("Качели", "add_later", 20, 0),
   ("Альпинист", "add_later", 16, 0),
   ("Боковые наклоны(влево)", "add_later", 12, 1),
   ("Боковые наклоны(вправо)", "add_later", 12, 1),
   ("Велосипед", "add_later", 15, 1),
   ("Планка", "add_later", 20, 0);

INSERT INTO 'workouts_exercises' ("workout_id", "exercise_id")
VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 2),
    (2, 5),
    (2, 6),
    (3, 1),
    (3, 2),
    (3, 4),
    (3, 6)
