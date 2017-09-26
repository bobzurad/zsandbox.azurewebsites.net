"""
http://zetcode.com/gui/pyqt5/eventssignals/
"""

import sys
from PyQt5.Qt import Qt
from PyQt5.QtWidgets import QWidget, QLCDNumber, QSlider, QVBoxLayout, QApplication

class Example(QWidget):
    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        lcd = QLCDNumber(self)
        slider = QSlider(Qt.Horizontal, self)

        vbox = QVBoxLayout()
        vbox.addWidget(lcd)
        vbox.addWidget(slider)

        self.setLayout(vbox)

        slider.valueChanged.connect(lcd.display)

        self.setGeometry(200, 200, 800, 600)
        self.setWindowTitle("Signal and Slot")
        self.show()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
