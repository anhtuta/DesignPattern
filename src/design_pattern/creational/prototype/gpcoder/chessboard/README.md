## Tạo cache bằng prototype

Giả sử ta có class Cell với chi phí tạo mới khá tốn kém:

```java
public class Cell {
    private String color;

    public Cell(String color) {
        this.color = color;
        try {
            // More time to create an cell
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "New Cell object has been created using constructor with color: " + color);
    }
}
```

Xây dựng bàn cờ vua 64 ô đen trắng. Trong ex1, ta sẽ tạo mới 64 cell xong in ra console, kq:

```
...
New Cell object has been created using constructor with color: WHITE
New Cell object has been created using constructor with color: BLACK
New Cell object has been created using constructor with color: WHITE
New Cell object has been created using constructor with color: BLACK
New Cell object has been created using constructor with color: WHITE
New Cell object has been created using constructor with color: BLACK
New Cell object has been created using constructor with color: WHITE
Time taken to create a board: 1010 millis
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
```

Ta thấy có 64 dòng `New Cell object has been created...` như vậy, tức là 64 object Cell đã được khởi tạo)

Thời gian tạo bàn cờ = 1010 ms, khá lâu! Tại sao phải tạo mới 64 cell làm gì, trong khi chỉ có 2 cell khác nhau được lặp lại 32 lần

=> Solution: tạo 2 cell với 2 màu đen trắng thôi, ta dùng 1 map làm cache (ex2):

```java
public class CellFactory {
    private static final Map<Color, Cell> CELL_CACHE = new HashMap<>();

    public static Cell getCell(Color color) {
        if (!CELL_CACHE.containsKey(color)) {
            Cell cell = new Cell(color.name());
            CELL_CACHE.put(color, cell);
        }
        return CELL_CACHE.get(color).clone();
    }
}
```

Run lại, thời gian tạo bàn cờ giảm đáng kể:

```
New Cell object has been created using constructor with color: WHITE
New Cell object has been created using constructor with color: BLACK
Time taken to create a board: 33 millis
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK]
Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE] Cell [color=BLACK] Cell [color=WHITE]
```

Nhưng nếu mỗi Cell cần có thêm thuộc tính `coordinate` nữa, giờ làm sao mà cache được! Bắt buộc phải tạo 64 Cell, nhưng do gọi constructor quá tốn time, nên ta có thể clone 2 object Cell 32 lần (ex3):

```java
public class Cell implements Cloneable {
    private String color;
    private String coordinate;

    @Override
    protected Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

`CellFactory` thay vì tạo mới Cell:

```java
return CELL_CACHE.get(color);
```

Bây giờ clone Cell đã có:

```java
return CELL_CACHE.get(color).clone();
```

Run lại, tuy time chạy chậm hơn ex2 (do phải clone nên có 64 object Cell, còn ex2 chỉ có 2 object), nhưng nó vẫn nhanh hơn nhiều ex1, và ta vẫn có thể in được tọa độ từng Cell

```
New Cell object has been created using constructor with color: WHITE
New Cell object has been created using constructor with color: BLACK
Time taken to create a board: 291 millis
Cell [color=WHITE, coordinate=0x0] Cell [color=BLACK, coordinate=0x1] Cell [color=WHITE, coordinate=0x2] Cell [color=BLACK, coordinate=0x3] Cell [color=WHITE, coordinate=0x4] Cell [color=BLACK, coordinate=0x5] Cell [color=WHITE, coordinate=0x6] Cell [color=BLACK, coordinate=0x7]
Cell [color=BLACK, coordinate=1x0] Cell [color=WHITE, coordinate=1x1] Cell [color=BLACK, coordinate=1x2] Cell [color=WHITE, coordinate=1x3] Cell [color=BLACK, coordinate=1x4] Cell [color=WHITE, coordinate=1x5] Cell [color=BLACK, coordinate=1x6] Cell [color=WHITE, coordinate=1x7]
Cell [color=WHITE, coordinate=2x0] Cell [color=BLACK, coordinate=2x1] Cell [color=WHITE, coordinate=2x2] Cell [color=BLACK, coordinate=2x3] Cell [color=WHITE, coordinate=2x4] Cell [color=BLACK, coordinate=2x5] Cell [color=WHITE, coordinate=2x6] Cell [color=BLACK, coordinate=2x7]
Cell [color=BLACK, coordinate=3x0] Cell [color=WHITE, coordinate=3x1] Cell [color=BLACK, coordinate=3x2] Cell [color=WHITE, coordinate=3x3] Cell [color=BLACK, coordinate=3x4] Cell [color=WHITE, coordinate=3x5] Cell [color=BLACK, coordinate=3x6] Cell [color=WHITE, coordinate=3x7]
Cell [color=WHITE, coordinate=4x0] Cell [color=BLACK, coordinate=4x1] Cell [color=WHITE, coordinate=4x2] Cell [color=BLACK, coordinate=4x3] Cell [color=WHITE, coordinate=4x4] Cell [color=BLACK, coordinate=4x5] Cell [color=WHITE, coordinate=4x6] Cell [color=BLACK, coordinate=4x7]
Cell [color=BLACK, coordinate=5x0] Cell [color=WHITE, coordinate=5x1] Cell [color=BLACK, coordinate=5x2] Cell [color=WHITE, coordinate=5x3] Cell [color=BLACK, coordinate=5x4] Cell [color=WHITE, coordinate=5x5] Cell [color=BLACK, coordinate=5x6] Cell [color=WHITE, coordinate=5x7]
Cell [color=WHITE, coordinate=6x0] Cell [color=BLACK, coordinate=6x1] Cell [color=WHITE, coordinate=6x2] Cell [color=BLACK, coordinate=6x3] Cell [color=WHITE, coordinate=6x4] Cell [color=BLACK, coordinate=6x5] Cell [color=WHITE, coordinate=6x6] Cell [color=BLACK, coordinate=6x7]
Cell [color=BLACK, coordinate=7x0] Cell [color=WHITE, coordinate=7x1] Cell [color=BLACK, coordinate=7x2] Cell [color=WHITE, coordinate=7x3] Cell [color=BLACK, coordinate=7x4] Cell [color=WHITE, coordinate=7x5] Cell [color=BLACK, coordinate=7x6] Cell [color=WHITE, coordinate=7x7]
```

## Ref

https://gpcoder.com/4413-huong-dan-java-design-pattern-prototype/
