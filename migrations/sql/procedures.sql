-- 1 (OK)
-- Процедура для обновления информации о лекарствах
-- CREATE PROCEDURE dbo.updateMedicationStock
--     @medicationId int,
--     @newCount int,
--     @newPrice float,
--     @dateOfManufacture date,
--     @expireDate date
-- AS
-- BEGIN
--     UPDATE dbo.medication
--     SET availableCount = @newCount, price = @newPrice, dateOfManufacture = @dateOfManufacture, expireDate = @expireDate
--     WHERE medicationId = @medicationId;
-- END;

-- EXEC dbo.updateMedicationStock 
--     @medicationId = 1, 
--     @newCount = 150, 
--     @newPrice = 199.9,
--     @dateOfManufacture = '2024-05-15',
--     @expireDate = '2027-05-15';

-- SELECT * FROM dbo.medication;

-- 2 (OK)
-- Процедура для расчета затрат на лечение пациента (выходные параметры)
-- CREATE PROCEDURE dbo.calculateTreatmentCosts
--     @treatmentId int,
--     @totalMedicationCost float OUTPUT,
--     @totalProcedureCost float OUTPUT,
--     @totalCost float OUTPUT
-- AS
-- BEGIN
--     -- Расчет стоимости лекарств
--     SELECT @totalMedicationCost = SUM(m.price * tm.amount)
--     FROM dbo.treatmentMedication tm
--     INNER JOIN dbo.medication m ON tm.medicationId = m.medicationId
--     WHERE tm.treatmentId = @treatmentId;

--     -- Расчет стоимости медицинских процедур
--     SELECT @totalProcedureCost = SUM(mp.price * tmp.amount)
--     FROM dbo.treatmentMedicalProcedure tmp
--     INNER JOIN dbo.medicalProcedure mp ON tmp.medicalProcedureId = mp.medicalProcedureId
--     WHERE tmp.treatmentId = @treatmentId;

--     -- Расчет итоговой общей стоимости
--     SET @totalCost = ISNULL(@totalMedicationCost, 0) + ISNULL(@totalProcedureCost, 0);
-- END;

-- -- Вызов процедуры
-- DECLARE @totalMedicationCost float;
-- DECLARE @totalProcedureCost float;
-- DECLARE @totalCost float;

-- EXECUTE dbo.calculateTreatmentCosts 
--     @treatmentId = 5,
--     @totalMedicationCost = @totalMedicationCost OUTPUT,
--     @totalProcedureCost = @totalProcedureCost OUTPUT,
--     @totalCost = @totalCost OUTPUT;

-- SELECT  @totalMedicationCost AS totalMedicationCost,
--         @totalProcedureCost AS totalProcedureCost, 
--         @totalCost AS totalCost;


-- 3 (OK)
-- Подсчет количества операций по докторам за заданный период (курсор + циклические)
-- CREATE PROCEDURE dbo.countSurgeriesByDoctor
--     @startDate DATETIME,
--     @endDate DATETIME
-- AS
-- BEGIN
--     DECLARE @doctorId INT;
--     DECLARE @lastName VARCHAR(255);
--     DECLARE @firstName VARCHAR(255);
--     DECLARE @middleName VARCHAR(255);
--     DECLARE @count INT;

--     DECLARE @results TABLE (
--         doctorId INT,
--         lastName VARCHAR(255),
--         firstName VARCHAR(255),
--         middleName VARCHAR(255),
--         surgeriesCount INT
--     );

--     DECLARE surgery_cursor CURSOR FOR 
--         SELECT doctorId, lastName, firstName, middleName
--         FROM dbo.doctor;

--     OPEN surgery_cursor;
--     FETCH NEXT FROM surgery_cursor INTO @doctorId, @lastName, @firstName, @middleName;

--     WHILE @@FETCH_STATUS = 0
--     BEGIN
--         SELECT @count = COUNT(*)
--         FROM dbo.surgery
--         JOIN dbo.doctorSurgeryJournal ON dbo.surgery.surgeryId = dbo.doctorSurgeryJournal.surgeryId
--         WHERE dbo.doctorSurgeryJournal.doctorId = @doctorId
--             AND dbo.surgery.startTime BETWEEN @startDate AND @endDate
--             AND dbo.surgery.endTime BETWEEN @startDate AND @endDate;

--         INSERT INTO @results (doctorId, lastName, firstName, middleName, surgeriesCount)
--         VALUES (@doctorId, @lastName, @firstName, @middleName, @count);

--         FETCH NEXT FROM surgery_cursor INTO @doctorId, @lastName, @firstName, @middleName;
--     END;

--     CLOSE surgery_cursor;
--     DEALLOCATE surgery_cursor;

--     SELECT * FROM @results;
-- END;

-- -- Вызов процедуры
-- DECLARE @startDate DATETIME = '2024-01-03';
-- DECLARE @endDate DATETIME = '2024-13-03';

-- EXEC dbo.countSurgeriesByDoctor @startDate, @endDate;


-- 4 (OK)
-- Получение первого освободившегося опытного доктора с операции (курсор + условные)
-- CREATE PROCEDURE dbo.getFirstAvailableDoctorBySpecializationAndExperience
--     @specializationName VARCHAR(100)
-- AS
-- BEGIN
--     DECLARE @specializationId INT;
    
--     SELECT @specializationId = specializationId
--     FROM dbo.specialization
--     WHERE specializationName = @specializationName;

--     IF @specializationId IS NULL
--     BEGIN
--         RAISERROR('Такой специальности нету в базе', 11, 1);
--         RETURN;
--     END

--     IF NOT EXISTS (SELECT 1 FROM dbo.doctorSpecialization WHERE specializationId = @specializationId)
--     BEGIN
--         RAISERROR('В больнице не трудоустроено таких специалистов на данный момент', 11, 1);
--         RETURN;
--     END

--     DECLARE @doctorId INT, @firstName VARCHAR(40), @lastName VARCHAR(40);
--     DECLARE @currentDateTime DATETIME = GETDATE();  --'2024-03-09T14:15:00'; - Пульмонолог

--     DECLARE doctor_cursor CURSOR FOR 
--         SELECT d.doctorId, d.firstName, d.lastName
--         FROM dbo.doctor d
--         INNER JOIN dbo.doctorSpecialization ds ON d.doctorId = ds.doctorId
--         INNER JOIN dbo.specialization s ON s.specializationId = ds.specializationId
--         WHERE s.specializationName = @specializationName
--         AND NOT EXISTS (
--             SELECT 1
--             FROM dbo.doctorSurgeryJournal dsj
--             INNER JOIN dbo.surgery su ON dsj.surgeryId = su.surgeryId
--             WHERE dsj.doctorId = d.doctorId
--             AND ((su.startTime <= @currentDateTime AND su.endTime IS NULL)
--             OR (su.startTime <= @currentDateTime AND @currentDateTime <= su.endTime))
--         )
--         ORDER BY ds.yearsOfExperience DESC;

--     OPEN doctor_cursor;

--     FETCH NEXT FROM doctor_cursor INTO @doctorId, @firstName, @lastName;

--     IF @@FETCH_STATUS = 0
--     BEGIN
--         SELECT @doctorId AS doctorId, @firstName AS firstName, @lastName AS lastName;
--     END
--     ELSE
--     BEGIN
--         RAISERROR('На данный момент все врачи данной специальности заняты', 11, 1);
--     END

--     CLOSE doctor_cursor;
--     DEALLOCATE doctor_cursor;
-- END;

-- EXEC dbo.getFirstAvailableDoctorBySpecializationAndExperience @specializationName = 'Пульмонолог'; -- Хирург -- Офтальмолог


-- 5 (OK)
-- Расчет общей стоимости лекарств для операции (выходные параметры)
-- CREATE PROCEDURE dbo.calculateTotalMedicationCost
--     @surgeryId INT,
--     @totalCost FLOAT OUTPUT
-- AS
-- BEGIN
--     SELECT @totalCost = SUM(m.price * ml.amount)
--     FROM dbo.medicationList ml
--     INNER JOIN dbo.medication m ON ml.medicationId = m.medicationId
--     WHERE ml.surgeryId = @surgeryId;
-- END;

-- -- Обращение к процедуре
-- DECLARE @totalCost FLOAT;

-- EXEC dbo.calculateTotalMedicationCost
--     @surgeryId = 1,
--     @totalCost = @totalCost OUTPUT;

-- SELECT @totalCost AS totalMedicationCost;