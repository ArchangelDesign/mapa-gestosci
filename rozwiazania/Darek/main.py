# EXAMPLE INPUT

# 10,20,30,40,50,60,
# 70,80,90,100,110,120,
# 1,2,3,4,5,6,
# 7,8,9,10,11,12,

def sum_neighbours(data, idx):
    return data[idx] \
          + (data[idx - 1] if idx > 0 else 0) \
          + (data[idx + 1] if idx + 1 < len(data) else 0)

def main():
    sum_all = 0
    with open("input.txt", 'r') as input_file, \
         open("output.txt", 'w') as output_file:

        content = input_file.readlines()
        data = [map(int, line.strip().split(',')) for line in content]

        for row_num in range(0, len(data)):
            for col_num in range(0, len(data[row_num])):
                sum = (sum_neighbours(data[row_num - 1], col_num) if row_num > 0 else 0) \
                    + (sum_neighbours(data[row_num], col_num)) \
                    + (sum_neighbours(data[row_num + 1], col_num) if row_num + 1 < len(data) else 0)
                sum_all = sum_all + sum
                output_file.write("{0},".format(sum))
            output_file.write('\n')
    print sum_all

if __name__ == "__main__":
    main()
