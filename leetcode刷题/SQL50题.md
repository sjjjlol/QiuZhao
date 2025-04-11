# [1280. 学生们参加各科测试的次数](https://leetcode.cn/problems/students-and-examinations/)

```sql
# Write your MySQL query statement below
select a.student_id, a.student_name, b.subject_name, count(c.subject_name) as attended_exams
from Students a  join Subjects b
left join Examinations c
on a.student_id = c.student_id
and b.subject_name = c.subject_name
group by 1, 3
order by 1,3
```



# [570. 至少有5名直接下属的经理](https://leetcode.cn/problems/managers-with-at-least-5-direct-reports/)

```sql
# Write your MySQL query statement below
select a.name
from Employee a left join Employee b
on a.id = b.managerId
group by a.id
having count(b.id) >= 5
```



# [1934. 确认率](https://leetcode.cn/problems/confirmation-rate/)

```sql
# Write your MySQL query statement below
select a.user_id,
round(ifnull(avg(b.action = 'confirmed'), 0), 2) as confirmation_rate
from Signups a 
left join Confirmations b
on a.user_id = b.user_id
group by 1
```



# [1251. 平均售价](https://leetcode.cn/problems/average-selling-price/)

```sql
SELECT p.product_id,
ROUND(ifnull(SUM(price * units)/SUM(units), 0), 2) AS average_price
FROM Prices p
LEFT JOIN UnitsSold s
ON p.product_id = s.product_id
AND s.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;
```



# [1633. 各赛事的用户注册率](https://leetcode.cn/problems/percentage-of-users-attended-a-contest/)

```sql
SELECT 
    b.contest_id,
    ROUND(COUNT(*) / (SELECT COUNT(*) FROM Users) * 100, 2) AS percentage
FROM Users a
LEFT JOIN Register b 
ON a.user_id = b.user_id
WHERE b.contest_id IS NOT NULL  -- 避免 NULL 影响计算
GROUP BY b.contest_id
ORDER BY percentage DESC, contest_id ASC;
```



# [1211. 查询结果的质量和占比](https://leetcode.cn/problems/queries-quality-and-percentage/)

```sql
select
    query_name,
    round(avg(rating/position),2) quality,
    round(100 * avg(rating < 3),2) poor_query_percentage
from
    Queries
group by
    query_name;
```

