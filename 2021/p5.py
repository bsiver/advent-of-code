from util import get_file_contents


class Point:
    x = None
    y = None

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __repr__(self):
        return f'{self.x}, {self.y}'

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __hash__(self):
        return hash((self.x, self.y))


def point_intersects(point: Point,
                     line: (Point, Point)):
    start_x = line[0].x
    end_x = line[1].x
    start_y = line[0].y
    end_y = line[1].y

    if start_y == end_y:
        x_range = range(min(start_x, end_x), (max(start_x, end_x) + 1))
        if point.y == start_y and point.x in x_range:
            return True

    if start_x == end_x:
        y_range = range(min(start_y, end_y), (max(start_y, end_y) + 1))
        if point.x == start_x and point.y in y_range:
            return True

    return False


def intersecting_points(line: (Point, Point)):
    start_x = line[0].x
    end_x = line[1].x
    start_y = line[0].y
    end_y = line[1].y
    x_range = range(min(start_x, end_x), (max(start_x, end_x) + 1))
    y_range = range(min(start_y, end_y), (max(start_y, end_y) + 1))

    intersections = []

    if start_x != end_x and start_y != end_y:
        return []

    for x in x_range:
        for y in y_range:
            intersections.append(Point(x, y))

    print(f'line {line} intersects {intersections}')
    return intersections


def intersecting_points_p2(line: (Point, Point)):
    start_x = line[0].x
    end_x = line[1].x
    start_y = line[0].y
    end_y = line[1].y
    intersections = []

    if start_x != end_x:
        x_range = range(start_x, end_x + int((end_x - start_x) / abs(end_x - start_x)), int((end_x - start_x) / abs(end_x - start_x)))
        for x in x_range:
            y = (start_y - end_y) / (start_x - end_x) * (x - start_x) + start_y
            intersections.append(Point(int(x), int(y)))
    elif start_y != end_y:
        y_range = range(start_y, end_y + int((end_y - start_y) / abs(end_y - start_y)), int((end_y - start_y) / abs(end_y - start_y)))
        for y in y_range:
            x = (start_x - end_x) / (start_y - end_y) * (y - start_y) + start_x
            intersections.append(Point(int(x), int(y)))

    return intersections


def print_board(intersection_counts):
    for y in range(max_y + 1):
        print()
        for x in range(max_y + 1):
            p = Point(x, y)
            print(f'{intersection_counts.get(p, ".")} ', end='')


lines = get_file_contents('p5.txt')
line_list = []
max_x = 0
max_y = 0
for line in lines:
    x = line.split(' -> ')
    x1 = int(x[0].split(',')[0])
    y1 = int(x[0].split(',')[1])
    x2 = int(x[1].split(',')[0])
    y2 = int(x[1].split(',')[1])
    start_point = Point(x1, y1)
    end_point = Point(x2, y2)
    line_list.append((start_point, end_point))
    max_x = max(x1, x2, max_x)
    max_y = max(y1, y2, max_y)

intersection_counts = {}
for line in line_list:
    ints = intersecting_points_p2(line)
    for p in ints:
        if p in intersection_counts.keys():
            intersection_counts[p] += 1
        else:
            intersection_counts[p] = 1

print_board(intersection_counts)

dangerous_points = len(list(filter(lambda v: type(v) == int and v >= 2,
                                   intersection_counts.values())))

print(f'Day 1 part 1: {dangerous_points}')



        

