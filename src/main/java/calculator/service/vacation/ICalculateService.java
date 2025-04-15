package calculator.service.vacation;

public interface ICalculateService <Vacation, Worker> {
    Vacation calculate(Worker worker);
}
