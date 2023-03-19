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
  "seconds"     INTEGER,
  "amounts"     INTEGER

);

CREATE TABLE "workouts_exercises" (
  "workout_id"		INTEGER NOT NULL,
  "exercise_id"		INTEGER NOT NULL

--  FOREIGN KEY("workout_id") REFERENCES "workouts"("id")
--    ON UPDATE CASCADE ON DELETE CASCADE,
--  FOREIGN KEY("exercise_id") REFERENCES "exercises"("id")
--    ON UPDATE CASCADE ON DELETE CASCADE
--  UNIQUE("workout_id","exercise_id")
);

INSERT INTO "workouts" ("workout_name", "image_background")
VALUES
  ("Руки", "@drawable/download"),
  ("Пресс", "@drawable/download"),
  ("Ноги", "@drawable/download"),
  ("Плечи и спина", "@drawable/download"),
  ("Грудь", "@drawable/download");

INSERT INTO "exercises" ("name", "animation", "seconds", "amounts")
VALUES
   ("Прыжки", "add_later", 30, null),
   ("Упражнение для пресса", "add_later", null, 16),
   ("Качели", "add_later", 20, null),
   ("Альпинист", "add_later", 16, null),
   ("Боковые наклоны(влево)", "add_later", null, 12),
   ("Боковые наклоны(вправо)", "add_later", null, 12),
   ("Велосипед", "add_later", null, 15),
   ("Планка", "add_later", 20, null);

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
