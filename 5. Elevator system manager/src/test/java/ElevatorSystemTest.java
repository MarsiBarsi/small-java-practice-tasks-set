import controllers.Elevator;
import controllers.ElevatorManager;
import controllers.Environment;
import controllers.Visitor;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import utils.TypicalElevatorsFactory;

import java.util.ArrayList;

@DisplayName("controllers.Environment")
public class ElevatorSystemTest {
    public Environment env;
    public ArrayList<Elevator> elevators;

    @BeforeAll
    static public void beforeAll() {
        ElevatorManager.ELEVATOR_MOVE_DELAY_MS = 1;
    }

    @BeforeEach
    public void beforeEach() {
        env = new Environment();
        elevators = new ArrayList<Elevator>();
    }

    @AfterEach
    public void afterEach() {
        env.turnElevatorSystemOff();
    }

    public void sleepTurn() {
        try {
            Thread.sleep(ElevatorManager.ELEVATOR_MOVE_DELAY_MS + 1);
        } catch (Exception ex) {}
    }

    public void sleepNTurns(int n) {
        for (int i = 0; i < n; i++) {
            sleepTurn();
        }
    }

    @Nested
    @DisplayName("с одним лифтом")
    class oneElevator {
        public Elevator elevator;

        @BeforeEach
        public void beforeEach() {
            elevator = new Elevator(1, 5, 3);
            elevators.add(elevator);
            env.setElevators(elevators);
        }

        @Nested
        @DisplayName("один посетитель")
        class oneVisitor {
            @Test
            @DisplayName("поднимается с 1 на 2 этаж")
            public void elevatorUp() {
                Visitor visitor1 = new Visitor(1, 2);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(2);

                Assert.assertTrue(visitor1.isFloorToReached());
            }

            @Test
            @DisplayName("остается на 1 этаже, если хочет на этаж меньше минимального этажа лифта")
            public void elevatorDown() {
                Visitor visitor1 = new Visitor(1, -1);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(2);

                Assert.assertFalse(visitor1.isFloorToReached());
            }

            @Test
            @DisplayName("поднимается с 1 на 5 этаж")
            public void elevatorUp4times() {
                Visitor visitor1 = new Visitor(1, 5);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(4);

                Assert.assertTrue(visitor1.isFloorToReached());
            }

            @Test
            @DisplayName("поднимается с 1 на 4 этаж, подхватывая посетителя на втором")
            public void elevatorUp3times() {
                Visitor visitor1 = new Visitor(2, 4);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(3);

                Assert.assertTrue(visitor1.isFloorToReached());
            }

            @Test
            @DisplayName("остается на первом этаже, если лифт ограничен им")
            public void elevatorKeepsStanding() {
                Visitor visitor1 = new Visitor(1, 2);

                elevator = new Elevator(1, 1,1);
                elevators = new ArrayList<Elevator>();
                elevators.add(elevator);
                env.setElevators(elevators);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertFalse(visitor1.isFloorToReached());
            }
        }

        @Nested
        @DisplayName("два посетителя")
        class twoVisitors {
            @Test
            @DisplayName("поднимаются с 1 на 2 этаж вместе")
            public void elevatorUp() {
                Visitor visitor1 = new Visitor(1, 2);
                Visitor visitor2 = new Visitor(1, 2);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("лифт поднимат первого с 1 на 5 этаж, подхватывая второго на 2 этаже")
            public void elevatorUp4times() {
                Visitor visitor1 = new Visitor(1, 5);
                Visitor visitor2 = new Visitor(2, 5);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepNTurns(4);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("лифт поднимает первого с 1 на 5 этаж, подхватывая второго на 4 этаже")
            public void elevatorUp4timesWithTwoVisitors() {
                Visitor visitor1 = new Visitor(1, 5);
                Visitor visitor2 = new Visitor(2, 5);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepNTurns(4);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("поднимаются с 1 до разных этажей")
            public void elevatorUp4timesWithTwoVisitorsDroppingOne() {
                Visitor visitor1 = new Visitor(1, 5);
                Visitor visitor2 = new Visitor(1, 3);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepNTurns(4);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("поднимаются с 1 до разных этажей")
            public void upWithDifferentVisitors() {
                Visitor visitor1 = new Visitor(1, 4);
                Visitor visitor2 = new Visitor(2, 3);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepNTurns(4);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("один едет с 1 до 5, второй регистрируется в процессе с 4 до 5")
            public void upWithDifferentVisitorsFromDifferentTime() {
                Visitor visitor1 = new Visitor(1, 5);
                Visitor visitor2 = new Visitor(4, 5);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepTurn();
                env.addVisitor(visitor2);
                sleepNTurns(5);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("лифт простаивает на 1, регистрируются двое с 3 до 5")
            public void upAfterWaiting() {
                Visitor visitor1 = new Visitor(3, 5);
                Visitor visitor2 = new Visitor(3, 5);

                env.turnElevatorSystemOn();
                sleepTurn();
                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                sleepNTurns(4);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("лифт поднял до 3 и там зарегистрировался новый пользователь вниз")
            public void upAndDown() {
                Visitor visitor1 = new Visitor(1, 3);
                Visitor visitor2 = new Visitor(3, 2);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(2);
                env.addVisitor(visitor2);
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("лифт поднял до 3 и там зарегистрировался новый пользователь вверх")
            public void upAndUp() {
                Visitor visitor1 = new Visitor(1, 3);
                Visitor visitor2 = new Visitor(3, 4);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(2);
                env.addVisitor(visitor2);
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }
        }
    }

    @Nested
    @DisplayName("несколько лифтов")
    class manyElevators {
        @BeforeEach
        public void beforeEach() {
            elevators = TypicalElevatorsFactory.generate(2, 1, 4, 3);
            env.setElevators(elevators);
        }

        @Nested
        @DisplayName("один посетитель")
        class oneVisitor {
            @Test
            @DisplayName("поднимается с 1 на 2 этаж на лифте 1, лифт 2 не едет никуда")
            public void elevatorUp() {
                Visitor visitor1 = new Visitor(1, 2);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertNull(elevators.get(1).getCurrentDirection());
            }

            @Test
            @DisplayName("если первый поехал за посетителем вверх, то второй остается ждать")
            public void firstUpSecondWait() {
                Visitor visitor1 = new Visitor(2, 3);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertNull(elevators.get(1).getCurrentDirection());
            }
        }

        @Nested
        @DisplayName("два посетителя")
        class twoVisitors {
            @Test
            @DisplayName("уехали вместе с 1 на 2 этаж на лифте 1, лифт 2 не едет никуда")
            public void elevatorUp() {
                Visitor visitor1 = new Visitor(1, 2);
                Visitor visitor2 = new Visitor(1, 2);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
                Assert.assertNull(elevators.get(1).getCurrentDirection());
            }

            @Test
            @DisplayName("лифт 1 взял посетителя поехал за посетителем вверх по пути, лифт 2 остается ждать")
            public void upAndGetMoreOne() {
                Visitor visitor1 = new Visitor(1, 3);
                Visitor visitor2 = new Visitor(2, 3);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.turnElevatorSystemOn();
                sleepNTurns(2);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
                Assert.assertNull(elevators.get(1).getCurrentDirection());
            }

            @Test
            @DisplayName("лифт 1 уехал вверх c посетителем, лифт 2 едет за новым")
            public void firstUpSecondWait() {
                Visitor visitor1 = new Visitor(1, 3);
                Visitor visitor2 = new Visitor(2, 4);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepTurn();
                env.addVisitor(visitor2);
                sleepNTurns(2);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }

            @Test
            @DisplayName("лифт 1 отвез вверх и взял там еще одного вниз, лифт 2 остался ждать")
            public void firstUpAndThenDownSecondWait() {
                Visitor visitor1 = new Visitor(1, 3);
                Visitor visitor2 = new Visitor(3, 2);

                env.addVisitor(visitor1);
                env.turnElevatorSystemOn();
                sleepNTurns(2);
                env.addVisitor(visitor2);
                sleepNTurns(2);

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
            }
        }

        @Nested
        @DisplayName("много посетителей")
        class manyVisitors {
            @Test
            @DisplayName("втроем уехали на лифте 1, лифт 2 стоит")
            public void firstUpAndSecondWait() {
                Visitor visitor1 = new Visitor(1, 2);
                Visitor visitor2 = new Visitor(1, 2);
                Visitor visitor3 = new Visitor(1, 2);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.addVisitor(visitor3);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
                Assert.assertTrue(visitor3.isFloorToReached());
                Assert.assertNull(elevators.get(1).getCurrentDirection());
            }

            @Test
            @DisplayName("трое уехали на лифте 1, лифт 2 взял четвертого")
            public void firstUpWithMaxAndSecondUp() {
                Visitor visitor1 = new Visitor(1, 2);
                Visitor visitor2 = new Visitor(1, 2);
                Visitor visitor3 = new Visitor(1, 2);
                Visitor visitor4 = new Visitor(1, 2);

                env.addVisitor(visitor1);
                env.addVisitor(visitor2);
                env.addVisitor(visitor3);
                env.addVisitor(visitor4);
                env.turnElevatorSystemOn();
                sleepTurn();

                Assert.assertTrue(visitor1.isFloorToReached());
                Assert.assertTrue(visitor2.isFloorToReached());
                Assert.assertTrue(visitor3.isFloorToReached());
                Assert.assertTrue(visitor4.isFloorToReached());
            }
        }
    }
}
