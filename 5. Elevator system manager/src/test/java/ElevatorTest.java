import controllers.Elevator;
import controllers.Visitor;
import enums.Direction;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("controllers.Elevator")
public class ElevatorTest {
    @Test
    @DisplayName("после создания находится на default этаже")
    public void created() {
        Elevator elevator = new Elevator(1, 5, 3);

        Assert.assertEquals(elevator.getCurrentFloor(), Elevator.DEFAULT_FLOOR);
    }

    @Nested
    @DisplayName("обычное состояние лифта")
    class up {
        @Test
        @DisplayName("едет вверх")
        public void elevatorUp() {
            Visitor visitor1 = new Visitor(1, 3);
            Elevator elevator = new Elevator(1, 5, 3);

            elevator.addVisitor(visitor1);

            int savedFloor = elevator.getCurrentFloor();

            Assert.assertEquals(elevator.move(), savedFloor + 1);
        }

        @Test
        @DisplayName("едет вниз")
        public void elevatorDown() {
            Visitor visitor1 = new Visitor(1, -1);
            Elevator elevator = new Elevator(-1, 2, 3);
            elevator.addVisitor(visitor1);

            int savedFloor = elevator.getCurrentFloor();

            Assert.assertEquals(elevator.move(), savedFloor - 1);
        }
    }

    @Nested
    @DisplayName("выход за границы")
    class OutOfRange {
        @Test
        @DisplayName("не едет вверх, если достигнут максимальный этаж")
        public void elevatorUpOutOfRange() {
            Visitor visitor1 = new Visitor(1, 3);
            Elevator elevator = new Elevator(1, 1, 3);
            elevator.addVisitor(visitor1);

            int savedFloor = elevator.getCurrentFloor();

            Assert.assertEquals(elevator.move(), savedFloor);
        }

        @Test
        @DisplayName("не едет вниз, если достигнут минимальный этаж")
        public void elevatorDownOutOfRange() {
            Visitor visitor1 = new Visitor(1, -1);
            Elevator elevator = new Elevator(1, 1, 3);
            elevator.addVisitor(visitor1);

            int savedFloor = elevator.getCurrentFloor();

            Assert.assertEquals(elevator.move(), savedFloor);
        }
    }

    @Nested
    @DisplayName("выход за границы")
    class direction {
        @Test
        @DisplayName("если лифт в ожидании, то его направление null")
        public void nil() {
            Elevator elevator = new Elevator(1, 1, 3);

            Assert.assertNull(elevator.getCurrentDirection());
        }

        @Test
        @DisplayName("корректно показывает путь вверх")
        public void up() {
            Visitor visitor1 = new Visitor(1, 3);
            Elevator elevator = new Elevator(1, 4, 3);
            elevator.addVisitor(visitor1);

            Assert.assertEquals(elevator.getCurrentDirection(), Direction.UP);
        }

        @Test
        @DisplayName("корректно показывает путь вниз")
        public void down() {
            Visitor visitor1 = new Visitor(1, -1);
            Elevator elevator = new Elevator(-2, 3, 3);
            elevator.addVisitor(visitor1);

            Assert.assertEquals(elevator.getCurrentDirection(), Direction.DOWN);
        }
    }
}
