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
            print(f'Row winner! {[str(b) for b in board[i]]}')
            return True
    # check cols, transpose board to compare cols
    cols = list(zip(*board))
    for j in range(len(cols)):
        if all([square.marked for square in cols[j]]):
            print('Col winner! {cols[j]}')
            return True

def play_bingo(numbers, boards):
    for n in numbers:
        for board in boards:
            for i in range(len(board)):
                for j in range(len(board)):
                    if board[i][j].val == n:
                        print(board[i][j], i, j)
                        board[i][j].marked = True
                        print(f'Marking {n} on board {i} {j}')
                        winner = is_winning(board)
                        if winner:
                            print('found winnar')
                            return n, board


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

print(f'numbers: {numbers} | boards: {boards}')
winning_number, winning_board = play_bingo(numbers, boards)
print(f'Winning #{winning_number}')

sum_unmarked = 0
for i in range(len(winning_board)):
    for j in range(len(winning_board)):
        if not winning_board[i][j].marked:
            sum_unmarked += winning_board[i][j].val

print (f'Day 4 part 1: {winning_number} | {sum_unmarked} | {winning_number * sum_unmarked}')


