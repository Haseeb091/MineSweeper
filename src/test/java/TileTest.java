import org.example.Tile;
import org.example.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TileTest {


    @Test
    public void testConstructor(){
        Tile testTile=new Tile(0);

        Assertions.assertEquals(false,testTile.getisMine(),"tile should not be mine");
        Assertions.assertEquals(false,testTile.getisVisible(),"tile should not be visible");
        Assertions.assertEquals(false,testTile.getisFlagged(),"tile should not be flagged");
        Assertions.assertEquals(0,testTile.getValue(),"there should not be any surrounding mines");


    }

    @Test
    public void flagGetAndSet(){
        Tile testTile=new Tile(0);
        testTile.setisFlagged(true);

        Assertions.assertTrue(testTile.getisFlagged(), "tile should be flagged but isnt");
        testTile.setisFlagged(false);
        Assertions.assertFalse(testTile.getisFlagged(), "tile should be flagged but isnt");

    }

    @Test
    public void visibleGetAndSet(){
        Tile testTile=new Tile(0);
        testTile.setisVisible(true);

        Assertions.assertTrue(testTile.getisVisible(), "tile should be visible but isnt");
        testTile.setisVisible(false);
        Assertions.assertFalse(testTile.getisVisible(), "tile should not be visible but is");

    }
    @Test
    public void mineGetAndSet(){
        Tile testTile=new Tile(0);
        testTile.setisMine(true);

        Assertions.assertTrue(testTile.getisMine(), "tile should be mine but isnt");
        testTile.setisMine(false);
        Assertions.assertFalse(testTile.getisMine(), "tile should not be mine but is");

    }
    @Test
    public void valueGetAndSet(){
        Tile testTile=new Tile(0);
        testTile.setValue(5);

        Assertions.assertEquals(5,testTile.getValue(), "value should be 5 but is "+testTile.getValue());
        testTile.setValue(10);
        Assertions.assertEquals(10,testTile.getValue(), "value should be 10 but is "+testTile.getValue());
        Grid g=new Grid(5,5);

    }
}
