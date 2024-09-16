# Mendel User Guide
![Demo screenshot of Mendel chatbot](Ui.png)

## Product Overview
Mendel is a chatbot application that helps users to remind themselves of various events including Todo, Event 
and Deadlines. It also supports additional utility features namely finding tasks by keyword and reminding users of
upcoming deadlines.

## Setup guide
1. Download Mendel-3.jar file from [here](https://github.com/Virusrwj223/ip/releases/tag/A-Release)
2. Move the downloaded file in your preferred directory
3. Open your terminal in this directory
4. Execute the command 
    ```
    java -jar Mendel-3.jar
    ```
---

## Feature summary

| Feature  | Command                                       | Description                                                      |
|----------|-----------------------------------------------|------------------------------------------------------------------|
| todo     | `todo <description>`                          | Writes a simple reminder note                                    |
| deadline | `deadline <description> /by <date>`           | Writes dealine note with due date                                |
| event    | `event <description> /from <date> /to <date>` | Writes event note from start to end date                         |
| mark     | `mark <serial>`                               | Ticks task of corresponding serial number                        |
| unmark   | `unmark <serial>`                             | Un-ticks task of corresponding serial number                     |
| delete   | `delete <serial>`                             | Removes task of corresponding serial number                      |
| find     | `find <keyword>`                              | Finds tasks whose description sub-matches the given expression   |
| remind   | `remind <date>`                               | Finds tasks whose date is before given date and unmarked         |
| list     | `list`                                        | Lists all tasks                                                  |
| bye      | `bye`                                         | Program exits                                                    |

---

### Adding todo

Writes a simple reminder note

`todo <description>`

**Example:**

```
todo complete CS2103T assignment
```

**Expected Output:**
```
Got it. I've added this task:
     [T][ ] test
Now you have 1 tasks in the list.
```

### Adding deadline

Writes a deadline with date the task is to be completed by.

`todo <description> /by <date>`[^1]

**Example:**

```
deadline CS2103T IP /by Sep 20 2024
```

**Expected Output:**
```
Got it. I've added this task:
   [D][ ] CS2103T IP (by: Sep 20 2024)
Now you have 2 tasks in the list.
```


### Adding event

Writes a event encapsulated within a start to end date range

`event <description> /from <date> /to <date>`[^1]

**Example:**

```
event CS2103T course /from Aug 10 2024 /to Dec 15 2024
```

**Expected Output:**
```
Got it. I've added this task:
   [E][ ] CS2103T course (from: Aug 10 2024 to Dec 15 2024)
Now you have 3 tasks in the list.
```

### Mark task

Marks a task syntatically as done

`mark <serial>`

**Example:**

```
mark 2
```

**Expected Output:**
```
Nice! I've marked this task as done:
   [D][X] CS2103T IP (by: Sep 20 2024)
```

### Unmark task

Marks a task syntatically as not done

`unmark <serial>`

**Example:**

```
unmark 2
```

**Expected Output:**
```
OK, I've marked this task as not done yet:
   [D][ ] CS2103T IP (by: Sep 20 2024)
```

### Delete task

Removes task from list

`delete <serial>`

**Example:**

```
delete 3
```

**Expected Output:**
```
Noted. I've removed this task:
   [E][ ] CS2103T course (from: Aug 10 2024 to Dec 15 2024)
Now you have 6 tasks in the list.
```

### Find task

Finds a task based on given keyword

`find <keyword>`

**Example:**

```
find CS2103T
```

**Expected Output:**
```
Here are the matching tasks in your list
   1.[D][ ] CS2103T IP (by: Sep 20 2024)
   2.[T][X] CS2103T assignment
```

### Remind task

Finds unmarked events and deadlines whose due date is before given input date

`remind <date>`[^1]

**Example:**

```
remind Dec 05 2024
```

**Expected Output:**
```
Here are the tasks with deadlines by Dec 05 2024
   1.[D][ ] CS2103T IP (by: Sep 20 2024)
```

### List tasks

Lists out all tasks currently in list

`list`

**Example:**

```
list
```

**Expected Output:**
```
Here are the tasks in your list:
   1.[D][ ] CS2103T IP (by: Sep 20 2024)
   2.[T][X] CS2103T assignment
```


[^1]: A variety of dateTime format is supported as outlined here: "dd-MMM-yyyy HH:mm", "dd/MMM/yyyy HH:mm", "dd MMM yyyy HH:mm", "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm", "d-MMM-yyyy HH:mm", "d/MMM/yyyy HH:mm", "d MMM yyyy HH:mm", "d-M-yyyy HH:mm", "d/M/yyyy HH:mm", "dd-MMM-yyyy HHmm", "dd/MMM/yyyy HHmm", "dd MMM yyyy HHmm", "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm", "dd-MMM-yyyy HH mm", "dd/MMM/yyyy HH mm", "dd MMM yyyy HH mm", "dd-MM-yyyy HH mm", "dd/MM/yyyy HH mm", "d/M/yyyy HH mm", "d/M/yyyy HHmm", "d/MMM/yyyy HH mm", "d/MMM/yyyy HHmm", "d-M-yyyy HH mm", "d-M-yyyy HHmm", "d-MMM-yyyy HH mm", "d-MMM-yyyy HHmm", "d MMM yyyy HHmm" "dd-MMM-yyyy", "dd/MMM/yyyy", "dd MMM yyyy", "MMM dd yyyy", "MMM, dd yyyy", "d MMM yyyy", "d/MMM/yyyy", "d-MMM-yyyy", "MMM d yyyy", "MMM, d yyyy", "MM dd yyyy", "dd MM yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy dd MM", "yyyy, dd MM", "dd-MM-yyyy", "MM d yyyy", "d MM yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy d MM", "yyyy, d MM", "d-MM-yyyy", "M dd yyyy", "dd M yyyy", "dd/M/yyyy", "M/dd/yyyy", "yyyy dd M", "yyyy, dd M", "dd-M-yyyy", "M d yyyy", "d M yyyy", "d/M/yyyy", "M/d/yyyy", "yyyy d M", "yyyy, d M", "d-M-yyyy"