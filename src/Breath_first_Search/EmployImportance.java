package Breath_first_Search;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a data structure of employee information, which includes the employee's unique id, 
 * his importance value and his direct subordinates' id.
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively.
 * Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. 
 * Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 * 
 * Now given the employee information of a company, and an employee id,
 * you need to return the total importance value of this employee and all his subordinates.
 * 
 * Example 1:
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3.
 * They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * 
 * Note:
 * 1. One employee has at most one direct leader and may have several subordinates.
 * 2. The maximum number of employees won't exceed 2000.
 */
public class EmployImportance {
	
	public static void main(String args[]) {
		EmployImportance ei = new EmployImportance();
		Employee e1 = new Employee();
		e1.id = 1;
		e1.importance = 5;
		List<Integer> sub1 = new ArrayList<Integer>();
		sub1.add(2);
		sub1.add(3);
		e1.subordinates = sub1;
		Employee e2 = new Employee();
		e2.id = 2;
		e2.importance = 3;
		e2.subordinates = new ArrayList<Integer>();
		Employee e3 = new Employee();
		e3.id = 3;
		e3.importance = 3;
		e3.subordinates = new ArrayList<Integer>();
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		int result = ei.getImportance(employees, 1);
		System.out.println(result);
	}
	
	public int getImportance(List<Employee> employees, int id) {
		int result = -1;
		if(employees == null || employees.size() == 0)
			return result;
		Map<Integer, Employee> map = new HashMap<Integer, Employee>();
		for(int i = 0; i < employees.size(); i++)
			map.put(employees.get(i).id, employees.get(i));
		Queue<Employee> theQueue = new LinkedList<Employee>();
		Employee employee = map.get(id);
		theQueue.offer(employee);
		int importance = 0;
		while(!theQueue.isEmpty()) {
			Employee cur = theQueue.poll();
			importance += cur.importance;
			for(int i = 0; i < cur.subordinates.size(); i++)
				theQueue.offer(map.get(cur.subordinates.get(i)));
		}
		result = importance;
		return result;        
    }
	
}

class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
}
