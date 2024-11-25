<?php
// Load the XML file
$xmlFile = 'data.xml';
$cycles = simplexml_load_file($xmlFile);

if ($cycles === false) {
    echo "Failed to load XML file.";
    foreach (libxml_get_errors() as $error) {
        echo "<br>" . $error->message;
    }
    exit;
}

// Loop through each cycle and display details
echo "<h1>Cycle Details</h1>";
foreach ($cycles->cycle as $cycle) {
    echo "<h2>Name: " . $cycle->name . "</h2>";
    echo "<p>Type: " . $cycle->type . "</p>";
    echo "<p>Price: $" . $cycle->price . "</p>";
}
?>
