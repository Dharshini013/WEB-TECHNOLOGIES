<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cycle Manager Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f9f9;
            text-align: center;
            margin: 0;
            padding: 20px;
        }
        form {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 300px;
            margin: 50px auto;
        }
        input {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
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
    </style>
</head>
<body>
    <h2>Cycle Manager Login</h2>
    <form onsubmit="return validateLogin();">
        <input type="text" id="username" name="username" placeholder="Username" required>
        <input type="password" id="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>
    </form>
    <script>
        function validateLogin() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            if (username === "admin" && password === "cyclepass") {
                window.location.href = "add_cycle_task.php";
            } else {
                alert("Invalid username or password!");
            }
            return false;
        }
    </script>
</body>
</html>
