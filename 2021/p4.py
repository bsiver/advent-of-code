from collections import OrderedDict
from util import get_file_contents

lines = get_file_contents('p4.txt')
numbers = list(map(int, lines[0].split(',')))

class Square:
    val = None
    marked = False

    def __init__(self, val, marked):
        self.val = val
        self.marked = marked

    def __str__(self):
        return str(self.val) + ' [x]' if self.marked else ' []'

def is_winning(board):
    # check rows
    for i in range(len(board)):
        if all([square.marked for square in board[i]]):
            return True
    # check cols, transpose board to compare cols
    cols = list(zip(*board))
    for j in range(len(cols)):
        if all([square.marked for square in cols[j]]):
            print('Col winner! {cols[j]}')
            return True


def unmarked_sum(b):
    sum_unmarked = 0
    for i in range(len(b)):
        for j in range(len(b)):
            if not b[i][j].marked:
                sum_unmarked += b[i][j].val
    return sum_unmarked


def play_bingo(numbers, boards):
    win_dict = OrderedDict()
    for n in numbers:
        for board_num in range(len(boards)):
            board = boards[board_num]
            for i in range(len(board)):
                for j in range(len(board)):
                    if board[i][j].val == n:
                        board[i][j].marked = True
                        winner = is_winning(board)
                        if winner:
                            if board_num not in list(win_dict.keys()):
                                print(f'found winnar: {board_num} | {unmarked_sum(board)}')
                                win_dict[board_num] = (n, unmarked_sum(board))
                            if len(win_dict.keys()) == len(boards):
                                return win_dict
    return win_dict


boards = []
board = []
for line in lines[1:]:
    row = []
    for num in line.split():
        row.append(Square(int(num), False))
    if row:
        board.append(row)
    if len(board) == 5:
        boards.append(board)
        board = []

winning_boards = play_bingo(numbers, boards)
first_winning_number = winning_boards[0][0]
first_winning_sum = winning_boards[0][1]
last_winning_number = winning_boards[next(reversed(winning_boards))][0]
last_winning_sum = winning_boards[next(reversed(winning_boards))][1]

print(f'Day 4 part 1: {first_winning_number} | {first_winning_sum} | {first_winning_number * first_winning_sum}')
print(f'Day 4 part 2: {last_winning_number} | {last_winning_sum} | {last_winning_number * last_winning_sum}')


