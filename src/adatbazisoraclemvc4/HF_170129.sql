-- Gabi
select
  case when e.department_id is null then null else min(d.department_name) end
    as department_name,
  e.department_id, e.first_name || ' ' || e.last_name as employee_name
from DEPARTMENTS d, EMPLOYEES e

where d.department_id=e.department_id or e.department_id is null

group by e.department_id, e.employee_id, e.first_name, e.last_name

order by department_name, employee_name;

-----------------------------------------
select d.department_name, e.first_name || ' ' || e.last_name as empName
from EMPLOYEES e
  left join DEPARTMENTS d
    on e.department_id=d.department_id

order by d.department_name, empName;

------------------------------------------

-- Lajos
SELECT DISTINCT (CASE WHEN E.DEPARTMENT_ID IS NULL THEN 'Semmi' ELSE DEPARTMENT_NAME END) depName, FIRST_NAME || ' ' || LAST_NAME AS empName
FROM DEPARTMENTS D, EMPLOYEES E
WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID OR E.DEPARTMENT_ID IS NULL
ORDER BY depName, empName;

-----------------------------------------------

-- Gabi
select
  case when e.department_id is null then null else min(d.department_name) end as department_name, e.first_name || ' ' || e.last_name as employee_name
from DEPARTMENTS d, EMPLOYEES e

where d.department_id=e.department_id or e.department_id is null

group by e.department_id, e.employee_id, e.first_name, e.last_name

order by department_name, employee_name;

-----------------------------------------

-- Feri (107-et ad vissza)
SELECT DISTINCT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID
FROM EMPLOYEES E
  FULL JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID;

------------------------------------------

-- Lajos
SELECT DISTINCT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  DEPARTMENT_NAME
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID;

--------------------------------------

-- Feri
SELECT DISTINCT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  DEPARTMENT_NAME
FROM EMPLOYEES E
  left JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE D.DEPARTMENT_NAME END DESC,
  E.FIRST_NAME, E.LAST_NAME;

--------------------------------------

-- Lajos

SELECT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN ' Nincs' ELSE DEPARTMENT_NAME END depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY
  depName, E.FIRST_NAME, E.LAST_NAME;

-----------------------------------------

-- Feri
SELECT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE DEPARTMENT_NAME END depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE depName END DESC,
  E.FIRST_NAME, E.LAST_NAME DESC NULLS FIRST;

-------------------------------------

-- Lajos
SELECT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE DEPARTMENT_NAME END depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY depName DESC, E.FIRST_NAME, E.LAST_NAME DESC;

---------------------------------------

-- Feri

SELECT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE DEPARTMENT_NAME END depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE depName END NULLS FIRST,
  E.FIRST_NAME, E.LAST_NAME;

-------------------------------------------

-- Lajos

SELECT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  DEPARTMENT_NAME depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY depName NULLS FIRST, E.FIRST_NAME, E.LAST_NAME DESC;

SELECT
  E.FIRST_NAME,
  E.LAST_NAME,
  E.DEPARTMENT_ID,
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN 'NINCS' ELSE DEPARTMENT_NAME END depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY DEPARTMENT_NAME NULLS FIRST, E.FIRST_NAME, E.LAST_NAME DESC;

------------------------------------

-- Feri

SELECT
  E.FIRST_NAME || ' ' || E.LAST_NAME empName,
  CASE WHEN E.DEPARTMENT_ID IS NULL THEN 'NINCS' ELSE DEPARTMENT_NAME END depName
FROM EMPLOYEES E
  LEFT JOIN DEPARTMENTS D ON
                            D.DEPARTMENT_ID=E.DEPARTMENT_ID
ORDER BY DEPARTMENT_NAME NULLS FIRST, E.FIRST_NAME, E.LAST_NAME DESC;

-- Balazs
proba git