<?php

class FileUtil
{
    const INPUT_FILE_PATH = "./input.txt";
    const OUTPUT_FILE_PATH = "./output.txt";

    public function loadInputFile()
    {
        if (!file_exists(self::INPUT_FILE_PATH))
            throw new Exception("File doesn't exist in '" . self::INPUT_FILE_PATH . "' path.");

        $fileString = strtolower(file_get_contents(self::INPUT_FILE_PATH));
        $fileContent = explode("\n", $fileString);

        $result = [];
        foreach ($fileContent as $row) {
            $result[] = explode(",", $row);
        }

        return $result;
    }

    public function saveOutputFile($data)
    {
        file_put_contents(self::OUTPUT_FILE_PATH, $data);
    }
}

class OutputBuilder
{
    private $fileUtil;

    private $sum = 0;

    public function __construct(FileUtil $fileUtil)
    {
        $this->fileUtil = $fileUtil;
    }

    public function build()
    {
        file_put_contents(FileUtil::OUTPUT_FILE_PATH, "");
        $input = $this->fileUtil->loadInputFile();
        for ($i = 0; $i < count($input); $i++) {
            for ($j = 0; $j < count($input[$i]); $j++) {
                $calculated = $this->calculate($input, $i, $j);
                $this->sum += $calculated;
                file_put_contents(FileUtil::OUTPUT_FILE_PATH, "$calculated,", FILE_APPEND);
            }
            file_put_contents(FileUtil::OUTPUT_FILE_PATH, "\n", FILE_APPEND);
        }

        echo $this->sum . PHP_EOL;
    }

    private function calculate($input, $row, $col)
    {
        if (!isset($input[$row][$col]))
            return 0;

        $result = $input[$row][$col];

        if (isset($input[$row][$col - 1]))
            $result += $input[$row][$col - 1];
        if (isset($input[$row][$col + 1]))
            $result += $input[$row][$col + 1];
        if (isset($input[$row - 1][$col]))
            $result += $input[$row - 1][$col];
        if (isset($input[$row + 1][$col]))
            $result += $input[$row + 1][$col];
        if (isset($input[$row - 1][$col - 1]))
            $result += $input[$row - 1][$col - 1];
        if (isset($input[$row + 1][$col + 1]))
            $result += $input[$row + 1][$col + 1];
        if (isset($input[$row - 1][$col + 1]))
            $result += $input[$row - 1][$col + 1];
        if (isset($input[$row + 1][$col - 1]))
            $result += $input[$row + 1][$col - 1];

        return $result;
    }
}

$outputBuilder = new OutputBuilder(new FileUtil());
$outputBuilder->build();
