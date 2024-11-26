<?php
$servername = "localhost";
$username = "root"; // Your MySQL username
$password = "";     // Your MySQL password
$db = "cycle_manager"; // Your database name

// Establish connection to the database
$conn = mysqli_connect($servername, $username, $password, $db);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Handle task deletion
if (isset($_GET['delete_id'])) {
    $task_id = $_GET['delete_id'];

    $delete_sql = "DELETE FROM cycle_tasks WHERE id = ?";
    if ($stmt = $conn->prepare($delete_sql)) {
        $stmt->bind_param("i", $task_id);
        if ($stmt->execute()) {
            echo "<p>Task deleted successfully.</p>";
        } else {
            echo "<p>Error deleting task: " . $stmt->error . "</p>";
        }
        $stmt->close();
    }
}

// Handle task addition
if (isset($_POST['add_task'])) {
    $description = $_POST['task_description'];
    $due_date = $_POST['due_date'];

    $insert_sql = "INSERT INTO cycle_tasks (task_description, due_date) VALUES (?, ?)";
    if ($stmt = $conn->prepare($insert_sql)) {
        $stmt->bind_param("ss", $description, $due_date);
        if ($stmt->execute()) {
            echo "<p>Task added successfully!</p>";
        } else {
            echo "<p>Error adding task: " . $stmt->error . "</p>";
        }
        $stmt->close();
    }
}

// Fetch all tasks
$sql = "SELECT * FROM cycle_tasks";
$result = $conn->query($sql);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cycle Manager</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef6f9;
            color: #333;
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        h1 {
            color: #2a9d8f;
        }
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            margin: 20px auto;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        input[type="text"], input[type="date"] {
            padding: 10px;
            width: calc(100% - 20px);
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            color: #fff;
            background-color: #2a9d8f;
            cursor: pointer;
        }
        button:hover {
            background-color: #217756;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #2a9d8f;
            color: #fff;
        }
        a {
            color: #e63946;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Cycle Maintenance Manager</h1>
    <p>Keep track of your cycle maintenance tasks and schedules with ease!</p>

    <!-- Task Addition Form -->
    <form action="" method="POST">
        <h2>Add a Cycle Task</h2>
        <input type="text" name="task_description" placeholder="Task Description" required>
        <input type="date" name="due_date" required>
        <button type="submit" name="add_task">Add Task</button>
    </form>

    <!-- Display Tasks -->
    <h2>Your Cycle Tasks</h2>
    <?php
    if ($result->num_rows > 0) {
        echo "<table>";
        echo "<tr><th>ID</th><th>Task</th><th>Due Date</th><th>Action</th></tr>";
        while ($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td>" . $row["id"] . "</td>";
            echo "<td>" . $row["task_description"] . "</td>";
            echo "<td>" . $row["due_date"] . "</td>";
            echo "<td><a href='?delete_id=" . $row["id"] . "'>Delete</a></td>";
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "<p>No tasks found. Add some tasks to get started!</p>";
    }

    // Close database connection
    $conn->close();
    ?>
</body>
</html>
