"""view model"""

from PySide2.QtCore import QObject, Property, Signal, Slot

class ViewModel(QObject):
    """view model"""

    _current_value = None

    propertyChanged = Signal()

    def __init__(self):
        QObject.__init__(self)

        self._current_value = 0
        self.propertyChanged.connect(self.on_property_changed)

    @Slot()
    def on_property_changed(self):
        pass

    # current_value Property
    def get_current_value(self):
        return str(self._current_value)
    def set_current_value(self, value):
        self._current_value = value
    current_value = Property(str, get_current_value, set_current_value, notify=propertyChanged)

    @Slot()
    def increment(self):
        """increments the current value"""
        self.set_current_value(self._current_value + 1)
        self.propertyChanged.emit()

    @Slot()
    def decrement(self):
        """decrements the current value"""
        self.set_current_value(self._current_value - 1)
        self.propertyChanged.emit()