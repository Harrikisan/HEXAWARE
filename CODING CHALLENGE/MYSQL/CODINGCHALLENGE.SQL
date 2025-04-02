-- INVESTIGATION DATABASE

-- HARRIKISAN M

-- 1.Select all open incidents.


select * from crime where status='Open';


-- 2.Find the total number of incidents.

select count(*) as incident_count from crime

-- 3.List all unique incident types.

select distinct(Incidenttype) from crime 

-- 4.Retrieve incidents that occurred between '2023-09-01' and '2023-09-10'.

select * from crime where incidentdate between '2023-09-01' and '2023-09-10'

-- 5.List persons involved in incidents in descending order of age.

alter table victim add age int

update victim set age=25 where victimid=3;
update victim set age=32 where victimid=2;
update victim set age=30 where victimid=1;

select name,victimid,crimeid,age from victim order by age desc;

-- 6.Find the average age of persons involved in incidents.

select avg(age) as average_age from victim 

-- 7.List incident types and their counts, only for open cases.

select incidenttype,count(*) as incident_count from crime where status='open'

-- 8.Find persons with names containing 'Doe'.

select name from victim where name rlike('Doe')

-- 9.Retrieve the names of persons involved in open cases and closed cases.

select v.name,c.status from victim v join crime c using(crimeid) where status rlike('Open|Closed')

-- 10.List incident types where there are persons aged 30 or 35 involved.

select c.incidenttype,v.name,v.age from crime c join victim v using (crimeid) where v.age rlike('30|35')

-- 11.Find persons involved in incidents of the same type as 'Robbery'.

select v.name,c.incidenttype from victim v join crime c using (crimeid) where c.incidenttype='Robbery'

-- 12.List incident types with more than one open case.

select incidenttype,count(status) as count_of_open from crime group by incidenttype having(count(status)>1)

-- 13.List all incidents with suspects whose names also appear as victims in other incidents.

select name from suspect where name in (select name from victim)

-- 14.Retrieve all incidents along with victim and suspect details.


select c.*,v.victimid,v.name,v.contactinfo,v.injuries,s.suspectid,s.name,s.description,s.criminalhistory 
from crime c join victim v using (crimeid) join suspect s using (crimeid)

-- 15.Find incidents where the suspect is older than any victim.

alter table suspect add age int;

update suspect set age=25 where suspectid=1;
update suspect set age=26 where suspectid=2

select c.*,s.name,s.age,v.name,v.age from suspect s join crime c using (crimeid) join victim v using (crimeid) 
where s.age>v.age group by crimeid

-- 16.Find suspects involved in multiple incidents:

select name,count(crimeid) from suspect group by crimeid having(count(*)>1)

-- 17.List incidents with no suspects involved.

select s.crimeid,c.incidenttype from suspect s join crime c using (crimeid) where name='Unknown'

-- 18.List all cases where at least one incident is of type 'Homicide' and all other incidents are of type
'Robbery'.

select * from crime group by crimeid having(
	sum(case when incidenttype rlike ('Homicide') then 1 else 0 end) and 
    sum(case when incidenttype not rlike ('Homicide|Theft') then 1 else 0 end)
)

-- 19.Retrieve a list of all incidents and the associated suspects, showing suspects for each incident, or
'No Suspect' if there are none.

select c.*,case when s.name not like 'Unknown' then s.name else 'No suspect' end as suspect
 from crime c join suspect s using (crimeid)

-- 20.List all suspects who have been involved in incidents with incident types 'Robbery' or 'Assault'

select s.name,s.crimeid,c.incidenttype from crime c join suspect s using(crimeid) where c.incidenttype rlike('Robbery|Assault')


