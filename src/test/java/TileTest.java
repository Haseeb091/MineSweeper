import org.example.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TileTest {


    @Test
    public void testConstructor(){
        Tile testTile=new Tile(0);

        Assertions.assertEquals(false,testTile.getisMine());
    }
}
