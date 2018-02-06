<?php
CONST OUTPUT = 'output.txt';
$arrayLines = explode("\n", file_get_contents('input.txt'));
foreach ($arrayLines as $key => $array) {
    $arrayLines[$key] = explode(',', $array);
}
$globalResult = 0;
$size = sizeof($arrayLines[0]) - 1;

foreach ($arrayLines as $row => $array) {
    foreach ($array as $key => $value) {
        $sum = addValues($arrayLines, $key, $row);
        $globalResult += $sum;
        addContentToFile($sum, $key, $size);
    }
}

echo $globalResult;

function addContentToFile($content, $key, $size)
{
    file_put_contents(OUTPUT, $content, FILE_APPEND);
    if ($key == $size)
        file_put_contents(OUTPUT, "\n", FILE_APPEND);
    else
        file_put_contents(OUTPUT, ",", FILE_APPEND);
}

function addValues($arrayLines, $key, $row)
{
    $result = 0;
    for ($i = $row - 1; $i <= $row + 1; $i++) {
        for ($j = $key - 1; $j <= $key + 1; $j++) {
            if (array_key_exists($i, $arrayLines) && array_key_exists($j, $arrayLines[$i]))
                $result += $arrayLines[$i][$j];
        }
    }

    return $result;
}