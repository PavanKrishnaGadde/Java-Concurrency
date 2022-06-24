package concepts.completableFutures;

public class EmployeeIdGenerator {

    public String generateId(int id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(id<1000) {
            throw new RuntimeException("ID not valid");
        }
        return "EMP_"+id;
    }
}
