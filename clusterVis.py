import tkinter as tk
import sys
import random

WIDTH = 1280
HEIGHT = 960

def create_window(width: int, height: int) -> (tk.Tk, tk.Canvas):
    root = tk.Tk()
    canvas =  tk.Canvas(root, width=width, height=height)
    canvas.pack()
    return (root, canvas)


class Point():
    def __init__(self, x: float, y: float, component: int = 0):
        self.x = x
        self.y = y
        self.component = component

    @staticmethod
    def parse(line: str):
        # comes as <x> <y>
        vals = line.split(" ")
        return Point(float(vals[0]), float(vals[1]), int(vals[2]))

colorDict = {}
def draw_point(canvas, point: Point):
    w = 3
    if point.component not in colorDict:
        colorDict[point.component] = random_color()
    return canvas.create_oval(point.x-w, point.y-w, point.x+w, point.y+w, fill=colorDict[point.component]);

def random_color() -> str:
    componentsDec = [random.randint(0, 255) for _ in ['r', 'g', 'b']]
    componentsHex = map(lambda x: hex(x).lstrip("0x").upper(), componentsDec)

    def addLeadingZeros(s):
        while len(s) < 2:
            s = '0' + s
        return s

    [rhex, ghex, bhex] = list(map(addLeadingZeros, componentsHex))

    return f"#{rhex}{ghex}{bhex}"

assert(len(sys.argv) == 2)
point_filename = sys.argv[1]

point_file = open(point_filename, "r")
points = []
for line in point_file:
    points.append(Point.parse(line))


root, canvas = create_window(WIDTH, HEIGHT)

for point in points:
    draw_point(canvas, point)


root.mainloop()

