def main():
    input_data = load_input('./input.txt')
    max_x, max_y = len(input_data[0]), len(input_data)
    output_data, overall = compute(max_x, max_y, input_data)
    save_output('./output.txt', output_data)
    print(overall)

def load_input(filename):
    return list(map(lambda row: list(map(int, row.strip().split(','))), open(filename, 'r').readlines()))

def compute(max_x, max_y, input_data):
    dispatcher = Dispatcher(max_x, max_y)
    output = []
    overall = 0

    for y in range(max_y):
        output.append([])
        for x in range(max_x):
            output[y].append(sum(map(lambda coords: input_data[coords[1]][coords[0]], dispatcher.get_coords(x, y))))
        overall += sum(output[y])

    return output, overall

def save_output(filename, output_data):
    with open(filename, 'w') as f:
        f.writelines(map(lambda row: ','.join(map(str, row)) + '\n', output_data))

class Dispatcher:
    MOVES = ['up', 'upright', 'right', 'downright', 'down', 'downleft', 'left', 'upleft']

    def __init__(self, boundry_x, boundry_y):
        self.boundry_x = boundry_x
        self.boundry_y = boundry_y

    def get_coords(self, x, y):
        for move in self.MOVES:
            neighbour_x, neighbour_y = getattr(self, move)(x, y)
            if 0 <= neighbour_x < self.boundry_x and 0 <= neighbour_y < self.boundry_y:
                yield (neighbour_x, neighbour_y)
        yield (x, y)

    def up(self, x, y):
        y -= 1
        return (x, y)

    def upright(self, x, y):
        x += 1
        y -= 1
        return (x, y)

    def right(self, x, y):
        x += 1
        return (x, y)

    def downright(self, x, y):
        x += 1
        y += 1
        return (x, y)

    def down(self, x, y):
        y += 1
        return (x, y)

    def downleft(self, x, y):
        x -= 1
        y += 1
        return (x, y)

    def left(self, x, y):
        x -= 1
        return (x, y)

    def upleft(self, x, y):
        x -= 1
        y -= 1
        return (x, y)

if __name__ == '__main__':
    main()