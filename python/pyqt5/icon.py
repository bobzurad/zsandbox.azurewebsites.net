"""
http://zetcode.com/gui/pyqt5/firstprograms/
"""

import sys
from PyQt5.QtWidgets import QApplication, QWidget
from PyQt5.QtGui import QIcon


class Example(QWidget):

    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        self.setGeometry(200, 200, 800, 600)
        self.setWindowTitle("Icon")
        self.setWindowIcon(QIcon('doomavatar.jpeg'))

        self.show()


if __name__ == '__main__':

    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
