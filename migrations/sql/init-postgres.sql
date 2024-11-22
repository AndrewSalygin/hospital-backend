CREATE TABLE patient (
    patientId SERIAL PRIMARY KEY,
    lastName VARCHAR(40) NOT NULL,
    firstName VARCHAR(40) NOT NULL,
    middleName VARCHAR(40),
    gender CHAR(1) NOT NULL,
    dateOfBirth DATE NOT NULL,
    phoneNumber VARCHAR(15) UNIQUE NOT NULL,
    insuranceInformation VARCHAR(255) NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE doctor (
    doctorId SERIAL PRIMARY KEY,
    lastName VARCHAR(40) NOT NULL,
    firstName VARCHAR(40) NOT NULL,
    middleName VARCHAR(40),
    dateOfBirth DATE NOT NULL,
    gender CHAR(1) NOT NULL,
    education TEXT NOT NULL,
    phoneNumber VARCHAR(15) UNIQUE NOT NULL,
    emailAddress VARCHAR(255),
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE disease (
    diseaseId SERIAL PRIMARY KEY,
    diseaseName VARCHAR(100) UNIQUE NOT NULL,
    diseaseCode VARCHAR(10) UNIQUE NOT NULL,
    commonDiseaseDuration VARCHAR(100)
);

CREATE TABLE surgery (
    surgeryId SERIAL PRIMARY KEY,
    patientId INT NOT NULL,
    scheduledDateTime TIMESTAMPTZ,
    startTime TIMESTAMPTZ,
    endTime TIMESTAMPTZ,
    surgeryType VARCHAR(100) NOT NULL,
    surgicalProcedureDescription TEXT,
    emergency BOOLEAN NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE patientJournal (
    medicalHistoryNoteId INT NOT NULL,
    patientId INT NOT NULL,
    doctorId INT NOT NULL,
    initialAdmission BOOLEAN NOT NULL,
    discharge BOOLEAN NOT NULL,
    patientStatus VARCHAR(100) NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE patientDiseasesJournal (
    diseaseId INT NOT NULL,
    patientId INT NOT NULL,
    dispensaryAccounting BOOLEAN NOT NULL
);

CREATE TABLE specialization (
    specializationId SERIAL PRIMARY KEY,
    specializationName VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE doctorSpecialization (
    doctorId INT NOT NULL,
    specializationId INT NOT NULL,
    yearsOfExperience INT NOT NULL
);

CREATE TABLE doctorSurgeryJournal (
    doctorId INT NOT NULL,
    surgeryId INT NOT NULL,
    workingHours FLOAT,
    scheduledWorkingHours FLOAT
);

CREATE TABLE medicalHistoryNote (
    medicalHistoryNoteId SERIAL PRIMARY KEY,
    admissionDateTime TIMESTAMPTZ NOT NULL,
    anamnesis TEXT NOT NULL
);

CREATE TABLE diseaseList (
    diseaseListId SERIAL PRIMARY KEY,
    diseaseId INT NOT NULL,
    medicalHistoryNoteId INT NOT NULL,
    treatmentId INT,
    resultsOfTreatment VARCHAR(255) NOT NULL DEFAULT ' '
);

CREATE TABLE medication (
    medicationId SERIAL PRIMARY KEY,
    medicationName VARCHAR(100) NOT NULL,
    medicationForm VARCHAR(100) NOT NULL,
    dosage VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100) NOT NULL,
    countryOfManufacture VARCHAR(100) NOT NULL,
    dateOfManufacture DATE NOT NULL,
    expireDate DATE NOT NULL,
    isPrescription BOOLEAN NOT NULL,
    price FLOAT NOT NULL,
    availableCount INT NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE medicationList (
    medicationId INT NOT NULL,
    surgeryId INT NOT NULL,
    amount INT NOT NULL
);

CREATE TABLE treatment (
    treatmentId SERIAL PRIMARY KEY,
    treatmentName VARCHAR(255) NOT NULL,
    doctorId INT NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE medicalProcedure (
    medicalProcedureId SERIAL PRIMARY KEY,
    medicalProcedureName VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE recipeJournal (
    recipeId INT NOT NULL,
    patientId INT NOT NULL,
    medicalHistoryNoteId INT NOT NULL
);

CREATE TABLE recipe (
    recipeId SERIAL PRIMARY KEY,
    medicationId INT NOT NULL,
    expirationDate DATE NOT NULL,
    isDeleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE treatmentMedication (
    treatmentId INT NOT NULL,
    medicationId INT NOT NULL,
    doctorInstructions TEXT,
    amount INT NOT NULL
);

CREATE TABLE treatmentMedicalProcedure (
    treatmentId INT NOT NULL,
    medicalProcedureId INT NOT NULL,
    doctorInstructions TEXT,
    amount INT NOT NULL
);

CREATE TABLE doctorMedicalProcedure (
    medicalProcedureId INT NOT NULL,
    doctorId INT NOT NULL
);

CREATE TABLE users (
    userId SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

ALTER TABLE surgery 
ADD CONSTRAINT fk_surgery_patient FOREIGN KEY (patientId) REFERENCES patient (patientId);

ALTER TABLE patientJournal 
ADD CONSTRAINT fk_patientJournal_patient FOREIGN KEY (patientId) REFERENCES patient (patientId),
ADD CONSTRAINT fk_patientJournal_doctor FOREIGN KEY (doctorId) REFERENCES doctor (doctorId);

ALTER TABLE patientDiseasesJournal 
ADD CONSTRAINT fk_patientDiseasesJournal_disease FOREIGN KEY (diseaseId) REFERENCES disease (diseaseId),
ADD CONSTRAINT fk_patientDiseasesJournal_patient FOREIGN KEY (patientId) REFERENCES patient (patientId);

ALTER TABLE doctorSpecialization 
ADD CONSTRAINT fk_doctorSpecialization_doctor FOREIGN KEY (doctorId) REFERENCES doctor (doctorId),
ADD CONSTRAINT fk_doctorSpecialization_specialization FOREIGN KEY (specializationId) REFERENCES specialization (specializationId);

ALTER TABLE doctorSurgeryJournal 
ADD CONSTRAINT fk_doctorSurgeryJournal_doctor FOREIGN KEY (doctorId) REFERENCES doctor (doctorId),
ADD CONSTRAINT fk_doctorSurgeryJournal_surgery FOREIGN KEY (surgeryId) REFERENCES surgery (surgeryId);

ALTER TABLE diseaseList 
ADD CONSTRAINT fk_diseaseList_disease FOREIGN KEY (diseaseId) REFERENCES disease (diseaseId),
ADD CONSTRAINT fk_diseaseList_medicalHistoryNote FOREIGN KEY (medicalHistoryNoteId) REFERENCES medicalHistoryNote (medicalHistoryNoteId),
ADD CONSTRAINT fk_diseaseList_treatment FOREIGN KEY (treatmentId) REFERENCES treatment (treatmentId);

ALTER TABLE medicationList 
ADD CONSTRAINT fk_medicationList_medication FOREIGN KEY (medicationId) REFERENCES medication (medicationId),
ADD CONSTRAINT fk_medicationList_surgery FOREIGN KEY (surgeryId) REFERENCES surgery (surgeryId);

ALTER TABLE recipeJournal 
ADD CONSTRAINT fk_recipeJournal_recipe FOREIGN KEY (recipeId) REFERENCES recipe (recipeId),
ADD CONSTRAINT fk_recipeJournal_patient FOREIGN KEY (patientId) REFERENCES patient (patientId),
ADD CONSTRAINT fk_recipeJournal_medicalHistoryNote FOREIGN KEY (medicalHistoryNoteId) REFERENCES medicalHistoryNote (medicalHistoryNoteId);

ALTER TABLE recipe 
ADD CONSTRAINT fk_recipe_medication FOREIGN KEY (medicationId) REFERENCES medication (medicationId);

ALTER TABLE treatmentMedication 
ADD CONSTRAINT fk_treatmentMedication_treatment FOREIGN KEY (treatmentId) REFERENCES treatment (treatmentId),
ADD CONSTRAINT fk_treatmentMedication_medication FOREIGN KEY (medicationId) REFERENCES medication (medicationId);

ALTER TABLE treatmentMedicalProcedure 
ADD CONSTRAINT fk_treatmentMedicalProcedure_treatment FOREIGN KEY (treatmentId) REFERENCES treatment (treatmentId),
ADD CONSTRAINT fk_treatmentMedicalProcedure_medicalProcedure FOREIGN KEY (medicalProcedureId) REFERENCES medicalProcedure (medicalProcedureId);

ALTER TABLE doctorMedicalProcedure 
ADD CONSTRAINT fk_doctorMedicalProcedure_medicalProcedure FOREIGN KEY (medicalProcedureId) REFERENCES medicalProcedure (medicalProcedureId),
ADD CONSTRAINT fk_doctorMedicalProcedure_doctor FOREIGN KEY (doctorId) REFERENCES doctor (doctorId);


INSERT INTO patient (lastName, firstName, middleName, gender, dateOfBirth, phoneNumber, insuranceInformation)
VALUES
('Иванов', 'Иван', 'Иванович', 'М', '1990-05-15', '+71234567890', 'Информация по страховке 1'),
('Петрова', 'Анна', 'Ивановна', 'Ж', '1985-08-20', '+79876543210', 'Информация по страховке 2'),
('Сидоров', 'Петр', 'Алексеевич', 'М', '1978-03-10', '+75555555555', 'Информация по страховке 3'),
('Козлова', 'Мария', NULL, 'Ж', '1995-11-25', '+79999999999', 'Информация по страховке 4'),
('Николаев', 'Алексей', 'Игоревич', 'М', '2010-09-30', '+71111111111', 'Информация по страховке 5'),
('Смирнова', 'Ольга', 'Александровна', 'Ж', '1990-10-15', '+79111141111', 'Информация по страховке 6'),
('Павлов', 'Алексей', NULL, 'М', '1953-01-20', '+79222225222', 'Информация по страховке 7'),
('Кузнецова', 'Ирина', 'Ивановна', 'Ж', '1978-08-10', '+793335433333', 'Информация по страховке 8'),
('Ковалев', 'Сергей', 'Павлович', 'М', '1995-05-25', '+79444447744', 'Информация по страховке 9'),
('Морозова', 'Екатерина', 'Сергеевна', 'Ж', '1982-12-30', '+79555655555', 'Информация по страховке 10');


INSERT INTO doctor (lastName, firstName, middleName, dateOfBirth, gender, education, phoneNumber, emailAddress)
VALUES
('Смирнов', 'Александр', 'Петрович', '1970-12-05', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Московский медицинский университет\nНаправление обучения: Общая медицина', '+71234567890', 'asmirnov@gmail.com'),
('Павлова', 'Елена', 'Анатольевна', '1983-07-15', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургский медицинский университет\nНаправление обучения: Педиатрия', '+79876543210', 'epavlova@mail.ru'),
('Кузнецов', 'Иван', 'Васильевич', '1965-02-20', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Киевский национальный медицинский университет\nНаправление обучения: Хирургия', '+75555555555', 'ikuznetsov'),
('Коваленко', 'Ольга', 'Владимировна', '1975-09-25', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Медицинская академия им. Сеченова (Москва)\nНаправление обучения: Акушерство и гинекология', '+79999999999', 'okovalenko@yandex.ru'),
('Морозов', 'Дмитрий', 'Александрович', '1980-04-10', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургская медицинская академия им. И. П. Павлова\nНаправление обучения: Офтальмология', '+71111111111', NULL),
('Козлов', 'Мария', 'Ивановна', '1985-04-25', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Медицинская академия им. Сеченова (Москва)\nНаправление обучения: Гастроэнтерология', '+79456789012', 'kozlov@mail.ru'),
('Николаев', 'Петр', 'Сергеевич', '1988-09-30', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургская медицинская академия им. И. П. Павлова\nНаправление обучения: Неврология', '+79567890623', 'nikolaev@mail.ru'),
('Петрова', 'Мария', 'Алексеевна', '1975-08-20', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургский медицинский университет\nНаправление обучения: Онкология', '+79234567890', 'petrova@yandex.ru'),
('Сидоров', 'Иван', 'Петрович', '1972-11-10', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Киевский национальный медицинский университет\nНаправление обучения: Ревматология', '+79345678901', NULL),
('Николаев', 'Павел', 'Сергеевич', '1980-04-15', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургская медицинская академия им. И. П. Павлова\nНаправление обучения: Эндокринология', '+79567890123', 'nikolaev@hospital.com'),
('Павлов', 'Александр', 'Петрович', '1978-07-15', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Санкт-Петербургский медицинский университет\nНаправление обучения: Кардиология', '+79789012345', 'pavlov@hospital.com'),
('Ковалев', 'Андрей', 'Иванович', '1982-09-25', 'М', 'Образование: Медицинское высшее образование\nВУЗ: Медицинский университет Минска\nНаправление обучения: Пульмонология', '+79901434567', 'kovalev@gmail.com'),
('Иванова', 'Анастасия', 'Сергеевна', '1986-03-25', 'Ж', 'Образование: Медицинское высшее образование\nВУЗ: Московский медицинский университет\nНаправление обучения: Физическая реабилитация и ЛФК', '+79998887766', 'ivanova@gmail.com');

INSERT INTO disease (diseaseName, diseaseCode, commonDiseaseDuration)
VALUES
('Бронхиальная астма', 'J45', NULL),
('Язва желудка', 'K25', NULL),
('Артроз', 'М15–М19', NULL),
('Эпилепсия', 'G40', NULL),
('Панкреатит', 'K85.9', NULL),
('Сколиоз', 'M41', NULL),
('ОРВИ', 'J06', '2-12 дней'),
('Грипп', 'J10', '2-10 дней'),
('Гастрит', 'K29', NULL),
('Пневмония', 'J18', '7-21 день'),
('Ангина', 'J03', '3-10 дней'),
('Артрит', 'M13', NULL),
('Сахарный диабет', 'E10-E14', NULL),
('Мигрень', 'G43', '4-72 часа'),
('Аппендицит', 'K35.9', '1-3 дня'),
('Гастрэктомия', 'Z93.1', NULL),
('Кесарево сечение', 'O82', NULL),
('Атеросклеротическая болезнь сердца', 'I25.1', NULL),
('Атеросклероз', 'I70.9', NULL),
('Рак груди', 'C50.9', NULL),
('Гипертония', 'I10-I15', NULL),
('Гепатит B', 'K75', '2-26 недель'),
('Хроническая бронхит', 'J41', NULL),
('Ревматоидный артрит', 'M05', NULL),
('Диабет', 'E11', NULL),
('Цистит', 'N30', '5-12 дней');

INSERT INTO medicalHistoryNote (admissionDateTime, anamnesis)
VALUES
('2024-03-08T14:15:00', 'Диагностирован хронический бронхит. Пациент жалуется на кашель, затрудненное дыхание и слабость. Принимает противокашлевые средства и противовоспалительные препараты.'),
('2024-03-10T15:45:00', 'Диагностирована язва желудка. Пациент жалуется на боли в эпигастральной области, изжогу после еды, тошноту и рвоту. Принимает препараты для снижения кислотности желудочного сока и антибиотики для лечения инфекции Helicobacter pylori.'),
('2024-03-11T09:00:00', 'Пациент страдает от артроза. Жалуется на боли и скованность в суставах, ухудшающуюся после физических нагрузок. Принимает противовоспалительные препараты и проходит курсы физиотерапии.'),
('2024-03-16T16:00:00', 'У пациента диагностирована эпилепсия. Имеются регулярные эпизоды судорог и потери сознания. Принимает антиэпилептические препараты для контроля эпилептических приступов.'),
('2024-03-17T08:15:00', 'Пациенту поставлен диагноз панкреатита. Жалуется на острые боли в эпигастральной области, тошноту, рвоту и общую слабость. Принимает противовоспалительные препараты и придерживается строгой диеты.'),
('2024-03-05T14:15:00', 'Диагностирован сколиоз. Пациент жалуется на боли в спине, деформацию позвоночника и ограничение подвижности. Принимает противовоспалительные препараты и проходит курсы физиотерапии и лфк.'),
('2024-03-08T14:15:00', 'Пациенту поставлен диагноз ОРВИ. Жалуется на насморк, кашель, боль в горле и общую слабость. Назначены антибиотики и средства от кашля.'),
('2024-03-15T14:00:00', 'Пациент здоров');

INSERT INTO patientJournal (medicalHistoryNoteId, patientId, doctorId, initialAdmission, discharge, patientStatus)
VALUES
(1, 1, 12, TRUE, FALSE, 'В процессе лечения'),
(2, 2, 6, TRUE, FALSE, 'В процессе лечения'),
(3, 3, 9, TRUE, FALSE, 'В процессе лечения'),
(4, 4, 3, TRUE, FALSE, 'В процессе лечения'),
(5, 5, 6, TRUE, FALSE, 'В процессе лечения'),
(6, 6, 3, TRUE, FALSE, 'В процессе лечения'),
(7, 7, 1, TRUE, FALSE, 'В процессе лечения'),
(8, 7, 1, FALSE, TRUE, 'Вылечен');


INSERT INTO patientDiseasesJournal (diseaseId, patientId, dispensaryAccounting)
VALUES
(1, 1, TRUE),
(2, 2, TRUE),
(3, 3, TRUE),
(4, 4, TRUE),
(5, 5, FALSE),
(6, 6, FALSE),
(7, 7, FALSE);

INSERT INTO medication (medicationName, medicationForm, dosage, manufacturer, countryOfManufacture, dateOfManufacture, expireDate, isPrescription, price, availableCount)
VALUES
('Амоксициллин', 'Таблетки', '500 мг', 'ФармХим', 'Россия', '2023-12-10', '2024-12-10', TRUE, 440, 30),
('Противокашлевый', 'Сироп', '10 мл', 'МедФарм', 'Россия', '2024-02-15', '2025-02-15', TRUE, 500, 100),
('Лидокаин', 'Инъекционный раствор', '5 мл', 'Аптека "Здоровье"', 'Россия', '2024-02-01', '2024-12-01', TRUE, 600, 50),
('Флуоресцеин', 'Порошок для приготовления раствора', '10 г', 'Медпрепараты', 'Россия', '2023-10-15', '2024-10-15', FALSE, 550, 100),
('Пропофол', 'Инъекционный раствор', '20 мл', 'ФармПроизводство', 'Россия', '2024-01-05', '2024-11-05', TRUE, 600, 20),
('Цефтриаксон', 'Порошок для приготовления раствора', '1 г', 'Медпоставки', 'Россия', '2023-11-20', '2024-11-20', FALSE, 320, 40),
('Ибупрофен', 'Таблетки', '200 мг', 'Медпрепараты', 'Россия', '2023-09-10', '2024-09-10', FALSE, 660, 25),
('Морфин', 'Инъекционный раствор', '10 мг', 'ФармХим', 'Россия', '2024-02-15', '2024-12-15', TRUE, 1250, 15),
('Левофлоксацин', 'Таблетки', '500 мг', 'Медпроизводство', 'Россия', '2023-08-20', '2024-08-20', FALSE, 1290, 35),
('Диклофенак', 'Таблетки', '50 мг', 'ФармПроизводство', 'Россия', '2023-11-01', '2024-11-01', FALSE, 870, 30),
('Вальпроевая кислота', 'Таблетки', '500 мг', 'Медпроизводство', 'Россия', '2023-12-20', '2024-12-20', FALSE, 950, 50),
('Метронидазол', 'Таблетки', '250 мг', 'ФармХим', 'Россия', '2023-10-05', '2024-10-05', FALSE, 1500, 40),
('Фентанил', 'Инъекционный раствор', '5 мг', 'Медпоставки', 'Россия', '2024-01-10', '2024-11-10', TRUE, 2300, 10),
('Меропенем', 'Порошок для приготовления раствора', '1 г', 'ФармПроизводство', 'Россия', '2023-09-25', '2024-09-25', FALSE, 1200, 60),
('Дексаметазон', 'Таблетки', '4 мг', 'ФармХим', 'Россия', '2023-11-15', '2024-11-15', FALSE, 1200, 45);

INSERT INTO treatment (treatmentName, doctorId)
VALUES
('Лечение 1', 1),
('Лечение 2', 1),
('Лечение 3', 1),
('Лечение 4', 1),
('Лечение 5', 1),
('Лечение 6', 1),
('Лечение 7', 1),
('Лечение 1', 3),
('Лечение 2', 3);

INSERT INTO treatmentMedication (treatmentId, medicationId, amount, doctorInstructions)
VALUES
(1, 1, 1, 'Принимать по 1 чайной ложке 2 раза в день после еды'),
(2, 2, 1, 'Принимать по 2 чайной ложке 2 раза в день после еды'),
(3, 3, 1, 'Принимать по 1 таблетке 1 раз в день после еды'),
(4, 4, 1, 'Принимать по 1 таблетке 1 раз в день после еды'),
(5, 5, 1, 'Принимать по 1 таблетке 1 раз в день после еды'),
(6, 6, 1, 'Принимать по 1 таблетке 1 раз в день после еды'),
(7, 7, 1, 'Принимать по 1 таблетке 1 раз в день после еды');

INSERT INTO diseaseList (diseaseId, medicalHistoryNoteId, treatmentId, resultsOfTreatment)
VALUES
(1, 1, 1, 'Улучшение после приема противокашлевых средств и противовоспалительных препаратов.'),
(2, 2, 2, 'Улучшение после приема препаратов для снижения кислотности желудочного сока и антибиотиков для лечения инфекции Helicobacter pylori.'),
(3, 3, 3, 'Улучшение после приема противовоспалительных препаратов и прохождения курсов физиотерапии.'),
(4, 4, 4, 'Контроль эпилептических приступов после приема антиэпилептических препаратов.'),
(5, 5, 5, 'Улучшение после приема противовоспалительных препаратов и соблюдения строгой диеты.'),
(6, 6, 6, 'Улучшение после приема противовоспалительных препаратов и прохождения курсов физиотерапии.'),
(7, 7, 2, 'Улучшение после приема средств от кашля.'),
(7, 7, 1, 'Улучшение после приема антибиотиков.');

-- ---
INSERT INTO surgery (patientId, scheduledDateTime, startTime, endTime, surgeryType, surgicalProcedureDescription, emergency)
VALUES
(1, '2024-03-09T14:15:00', '2024-03-09T12:15:00', '2024-03-09T16:15:00', 'Бронхоскопия', 'Исследование бронхов', FALSE),
(2, NULL, '2024-03-11T15:45:00', '2024-03-11T17:45:00', 'Лапароскопия', 'Исследование брюшной полости', FALSE),
(3, '2024-03-12T09:00:00', '2024-03-12T09:00:00', '2024-03-12T12:00:00', 'Артропластика', 'Восстановление сустава', FALSE),
(4, NULL, '2024-03-17T16:00:00', NULL, 'Эпилепсэктомия', 'Хирургическое лечение эпилепсии', TRUE),
(5, '2024-03-18T08:15:00', '2024-03-18T08:15:00', '2024-03-18T15:15:00', 'Панкреатэктомия', 'Удаление всей поджелудочной железы', FALSE);

INSERT INTO specialization (specializationName)
VALUES
('Терапевт'),
('Хирург'),
('Оториноларинголог'),
('Акушер-гинеколог'),
('Офтальмолог'),
('Гастроэнтеролог'),
('Невролог'),
('Онколог'),
('Ревматолог'),
('Эндокринолог'),
('Кардиолог'),
('Пульмонолог'),
('Врач ЛФК'),
('Ортопед'),
('Травматолог');

INSERT INTO medicalProcedure (medicalProcedureName, price)
VALUES
('Физиотерапия', 800),
('Массаж', 1000),
('ЛФК', 1500),
('ЭКГ', 2000),
('УЗИ', 2000);

INSERT INTO recipe (medicationId, expirationDate)
VALUES
(1, '2024-03-28'),
(13, '2024-03-30');

INSERT INTO medicationList (medicationId, surgeryId, amount)
VALUES
-- Бронхоскопия
(3, 1, 1), -- Лидокаин (Анестезия)
(4, 1, 1), -- Флуоресцеин (Контрастное средство)
(1, 1, 1), -- Амоксициллин (Антибиотик)

-- Лапароскопия
(5, 2, 1), -- Пропофол (Анестезия)
(2, 2, 1), -- Противокашлевый (Препарат)

-- Артропластика
(1, 3, 1), -- Амоксициллин (Антибиотик)

-- Эпилепсэктомия
(7, 4, 1), -- Морфин (Обезболивание)

-- Панкреатэктомия
(8, 5, 1); -- Левофлоксацин (Антибиотик)


-- 1 (OK) (OK)
CREATE VIEW doctorV AS
SELECT doctor.doctorId, doctor.lastName, doctor.firstName, doctor.middleName, doctor.dateOfBirth, doctor.gender, 
       specialization.specializationName, doctorSpecialization.yearsOfExperience, doctor.isDeleted
FROM doctor
JOIN doctorSpecialization ON doctor.doctorId = doctorSpecialization.doctorId
JOIN specialization ON doctorSpecialization.specializationId = specialization.specializationId;

-- 2 (OK) (OK)
CREATE VIEW recipeV AS
SELECT recipe.recipeId, medicalHistoryNote.admissionDateTime, recipe.expirationDate, medication.medicationName, medication.medicationForm, medication.dosage,
       patient.lastName AS patientLastName, patient.firstName AS patientFirstName, patient.middleName AS patientMiddleName, 
       doctor.lastName AS doctorLastName, doctor.firstName AS doctorFirstName, doctor.middleName AS doctorMiddleName, 
       recipe.isDeleted
FROM recipe
JOIN medication ON recipe.medicationId = medication.medicationId
JOIN recipeJournal ON recipe.recipeId = recipeJournal.recipeId
JOIN medicalHistoryNote ON recipeJournal.medicalHistoryNoteId = medicalHistoryNote.medicalHistoryNoteId
JOIN patientJournal ON patientJournal.medicalHistoryNoteId = medicalHistoryNote.medicalHistoryNoteId
JOIN patient ON patientJournal.patientId = patient.patientId
JOIN doctor ON patientJournal.doctorId = doctor.doctorId;


-- 3 (OK) (OK)
CREATE VIEW patientDiseaseV AS
SELECT patient.lastName, patient.firstName, patient.middleName, patient.gender, patient.dateOfBirth, 
       disease.diseaseCode, disease.diseaseName, patientDiseasesJournal.dispensaryAccounting
FROM disease
JOIN patientDiseasesJournal ON disease.diseaseId = patientDiseasesJournal.diseaseId
JOIN patient ON patientDiseasesJournal.patientId = patient.patientId;

-- 4 (OK) (OK)
CREATE VIEW doctorSpecializationInsertV AS
SELECT * 
FROM doctorSpecialization 
WHERE yearsOfExperience >= 0
WITH CHECK OPTION;

-- 5 (OK) (OK)
CREATE VIEW recipeInsertV AS
SELECT * 
FROM recipe
WHERE expirationDate >= CURRENT_DATE
WITH CHECK OPTION;

-- 6 (OK) (OK)
CREATE VIEW patientInsertV AS
SELECT *
FROM patient
WHERE gender IN ('М', 'Ж')
WITH CHECK OPTION;

-- 1 (OK) (OK)
-- Процедура для обновления информации о лекарствах
CREATE OR REPLACE FUNCTION updateMedicationStock(medicationId INT, newCount INT, newPrice FLOAT, dateOfManufacture DATE, expireDate DATE)
RETURNS VOID AS $$
BEGIN
    UPDATE medication
    SET availableCount = newCount, price = newPrice, dateOfManufacture = dateOfManufacture, expireDate = expireDate
    WHERE medicationId = medicationId;
END;
$$ LANGUAGE plpgsql;

-- 2 (OK) (OK)
-- Процедура для расчета затрат на лечение пациента (выходные параметры)
-- CREATE OR REPLACE FUNCTION calculateTreatmentCosts(treatmentId INT, OUT totalMedicationCost FLOAT, OUT totalProcedureCost FLOAT, OUT totalCost FLOAT)
-- RETURNS VOID AS $$
-- BEGIN
--     -- Расчет стоимости лекарств
--     SELECT SUM(m.price * tm.amount) INTO totalMedicationCost
--     FROM treatmentMedication tm
--     JOIN medication m ON tm.medicationId = m.medicationId
--     WHERE tm.treatmentId = treatmentId;

--     -- Расчет стоимости медицинских процедур
--     SELECT SUM(mp.price * tmp.amount) INTO totalProcedureCost
--     FROM treatmentMedicalProcedure tmp
--     JOIN medicalProcedure mp ON tmp.medicalProcedureId = mp.medicalProcedureId
--     WHERE tmp.treatmentId = treatmentId;

--     -- Расчет итоговой общей стоимости
--     totalCost := COALESCE(totalMedicationCost, 0) + COALESCE(totalProcedureCost, 0);
-- END;
-- $$ LANGUAGE plpgsql;

-- 3 (OK) (OK)
-- Подсчет количества операций по докторам за заданный период (курсор + циклические)
-- CREATE OR REPLACE FUNCTION countSurgeriesByDoctor(startDate TIMESTAMP, endDate TIMESTAMP)
-- RETURNS TABLE(doctorId INT, lastName VARCHAR, firstName VARCHAR, middleName VARCHAR, surgeriesCount INT) AS $$
-- DECLARE
--     surgery_cursor CURSOR FOR SELECT doctorId, lastName, firstName, middleName FROM doctor;
--     doctorRecord RECORD; -- Используем RECORD для хранения результатов
-- BEGIN
--     OPEN surgery_cursor;
--     LOOP
--         FETCH surgery_cursor INTO doctorRecord;
--         EXIT WHEN NOT FOUND;

--         SELECT COUNT(*) INTO surgeriesCount
--         FROM surgery
--         JOIN doctorSurgeryJournal ON surgery.surgeryId = doctorSurgeryJournal.surgeryId
--         WHERE doctorSurgeryJournal.doctorId = doctorRecord.doctorId
--         AND surgery.startTime BETWEEN startDate AND endDate
--         AND (surgery.endTime BETWEEN startDate AND endDate OR surgery.endTime IS NULL); -- Учитываем NULL для endTime

--         RETURN NEXT doctorRecord.doctorId, doctorRecord.lastName, doctorRecord.firstName, doctorRecord.middleName, surgeriesCount;
--     END LOOP;

--     CLOSE surgery_cursor; -- Закрываем курсор (опционально)
-- END;
-- $$ LANGUAGE plpgsql;



-- 4 (OK) (OK)
-- Получение первого освободившегося опытного доктора с операции (курсор + условные)
CREATE OR REPLACE FUNCTION getFirstAvailableDoctorBySpecializationAndExperience(specializationName VARCHAR)
RETURNS TABLE(doctorId INT, firstName VARCHAR, lastName VARCHAR) AS $$
DECLARE
    specializationId INT;
    doctor_cursor CURSOR FOR
        SELECT d.doctorId, d.firstName, d.lastName
        FROM doctor d
        JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId
        JOIN specialization s ON s.specializationId = ds.specializationId
        WHERE s.specializationName = specializationName
        AND NOT EXISTS (
            SELECT 1
            FROM doctorSurgeryJournal dsj
            JOIN surgery su ON dsj.surgeryId = su.surgeryId
            WHERE dsj.doctorId = d.doctorId
            AND (su.startTime <= NOW() AND su.endTime IS NULL OR NOW() <= su.endTime)
        )
        ORDER BY ds.yearsOfExperience DESC;
BEGIN
    OPEN doctor_cursor;
    FETCH NEXT FROM doctor_cursor INTO specializationId, doctorId, firstName, lastName;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'На данный момент все врачи данной специальности заняты';
    END IF;
END;
$$ LANGUAGE plpgsql;

-- 5 (OK) (OK)
-- Расчет общей стоимости лекарств для операции (выходные параметры)
-- CREATE OR REPLACE FUNCTION calculateTotalMedicationCost(surgeryId INT, OUT totalCost FLOAT)
-- RETURNS VOID AS $$
-- BEGIN
--     SELECT SUM(m.price * ml.amount) INTO totalCost
--     FROM medicationList ml
--     JOIN medication m ON ml.medicationId = m.medicationId
--     WHERE ml.surgeryId = surgeryId;
-- END;
-- $$ LANGUAGE plpgsql;