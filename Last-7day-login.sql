-- H2 inquiry last 7day active user
select DISTINCT B.NAME, B.EMAIL
from LOGIN_HISTORY A
join CUSTOMER_DETAIL B ON A.CUSTOMER_ACCOUNT_ID = B.CUSTOMER_ACCOUNT
where LOGIN_DATE > DateAdd(DAY,-7,CURRENT_TIMESTAMP())