package design_pattern.structural.adapter.example1;

/*
 * Adapter Pattern là pattern giữ vai trò trung gian giữa hai lớp, 
 * chuyển đổi giao diện của một hay nhi�?u lớp có sẵn thành một giao 
 * diện khác, thích hợp cho lớp đang viết. �?i�?u này cho phép các lớp 
 * có các giao diện khác nhau có thể dễ dàng giao tiếp tốt với nhau 
 * thông qua giao diện trung gian, không cần thay đổi code của lớp 
 * có sẵn cũng như lớp đang viết. Adapter Pattern còn g�?i là Wrapper 
 * Pattern do cung cấp một giao diện “b�?c ngoài�? tương thích cho một 
 * hệ thống có sẵn, có dữ liệu và hành vi phù hợp nhưng có giao diện 
 * không tương thích với lớp đang viết.
 * 
 * Adapter trong thực tế:
 * Adapter là một khái niệm rất thông dụng trong đ�?i sống hàng ngày. 
 * Ta thư�?ng hay bắt gặp các loại adapter như: power adapter (chuyển 
 * đổi điện áp), laptop adapter (bộ sạc của laptop) hay memory card 
 * adapter… Các adapter này có nhiệm vụ chính là làm cầu nối trung 
 * gian để giúp hai đồ vật gì đó có thể hoạt động với nhau. Ví dụ như 
 * laptop không sử dụng nguồn điện xoay chi�?u 224V, nên để laptop có 
 * thể sử dụng được nguồn điện 224V cần có một adapter làm cầu nối 
 * trung gian để chuyển nguồn điện xoay chi�?u 224V thành nguồn điện 1 
 * chi�?u 12V. Một ví dụ khác là thẻ nhớ, trên thị trư�?ng có rất nhi�?u 
 * loại thẻ nhớ nhưng loại thịnh hành nhất ngày nay vẫn là loại micro-SD 
 * vì tính nh�? g�?n và phổ biến của nó, vậy nếu bạn có một thẻ micro-SD 
 * và một máy ảnh sử dụng thẻ SD, làm sao để có thể cắm thẻ micro-SD này 
 * vào máy ảnh? Khi đó ta sẽ sử dụng một adapter để chuyển “b�? ngoài�? 
 * của thẻ micro-SD thành SD để có thể cắm vào máy ảnh.
 * 
 * �?ể hiểu v�? sơ đồ mô tả Adapter Pattern thì trước hết bạn phải hiểu v�? 3 khái niệm:
	1. Client: �?ây là lớp sẽ sử dụng đối tượng của bạn (đối tượng mà bạn muốn 
	chuyển đổi giao diện).
	2. Adaptee: �?ây là những lớp bạn muốn lớp Client sử dụng, nhưng hiện th�?i 
	giao diện của nó không phù hợp.
	3. Adapter: �?ây là lớp trung gian, thực hiện việc chuyển đổi giao diện cho 
	Adaptee và kết nối Adaptee với Client.

 * Xem thêm tại: https://viblo.asia/p/design-patterns-adapter-pattern-WEMkBpqyGQK
 * (Nên xem thêm!!!)
 * 
 * Code Example in tutorialpoint.com:
 * We have a MediaPlayer interface and a concrete class AudioPlayer 
 * implementing the MediaPlayer interface. AudioPlayer can play mp3 
 * format audio files by default.
 * 
 * We want to make AudioPlayer to play other formats as well. To 
 * attain this, we have created an adapter class MediaAdapter which 
 * implements the MediaPlayer interface and uses AdvancedMediaPlayer 
 * objects to play the required format.
 * 
 */
public interface MediaPlayer {
	public void play(String audioType, String fileName) throws Exception;
}
