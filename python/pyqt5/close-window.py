"""
http://zetcode.com/gui/pyqt5/firstprograms/
"""

import sys
from PyQt5.QtWidgets import QWidget, QPushButton, QApplication
from PyQt5.QtCore import QCoreApplication, pyqtSlot


class Example(QWidget):
    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):
        qbtn = QPushButton('Quit', self)
        # this next line from the tutoral is wrong (doesn't work with PyQt5)
        # qbtn.clicked.connect(QCoreApplication.instance().quit)
        qbtn.clicked.connect(self.qbtn_clicked)  # use this line instead
        qbtn.resize(qbtn.sizeHint())
        qbtn.move(50, 50)

        self.setGeometry(200, 200, 800, 600)
        self.setWindowTitle("Quit Button")
        self.show()

    @pyqtSlot()
    def qbtn_clicked(self):
        QCoreApplication.instance().quit()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
