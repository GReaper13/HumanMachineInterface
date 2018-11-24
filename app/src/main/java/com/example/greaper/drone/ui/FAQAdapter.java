package com.example.greaper.drone.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greaper.drone.R;
import com.example.greaper.drone.data.FAQ;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    private LayoutInflater inflater;
    private List<FAQ> list = new ArrayList<>();

    public FAQAdapter(Context context) {
        inflater = LayoutInflater.from(context);

        list.add(new FAQ("Tôi không thể kết nối được với drone", "Vui lòng thử các bước sau:\n1. Kiểm tra tình trạng pin của drone.\n2. Đảm bảo rằng drone đang hoạt động bình thường. Nếu drone không hoạt động, vui lòng mang drone trở lại trung tâm để tiến hành kiểm tra.\n3. Đảm bảo rằng thiết bị của bạn đang bật kết nối mạng.\n4. Đảm bảo mật khẩu đã được nhập đúng\n5. Nếu vẫn không thể kết nối, vui lòng mang đến trung tâm để tiến hành kiểm tra và sửa chữa."));
        list.add(new FAQ("Tôi không xem được video", "Vui lòng kiểm tra lại kết nối mạng của mình. Có thể đường truyền mạng đang có vấn đề."));
        list.add(new FAQ("Tôi chỉ thấy màn hình đen và không xem được video", "1. Chọn đúng drone muốn xem/điều khiển\n2. Bật kết nối mạng của thiết bị.\n3. Thu hồi và kiếm tra camera của drone.\n4. Vui lòng mang đến trung tâm để kiểm tra và sửa chữa."));
        list.add(new FAQ("Tôi không xem được video của tháng trước", "Hệ thống chỉ cho phép lưu trữ video của 30 ngày gần nhất. Xin lỗi vì sự bất tiện này."));

    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.faq_item, viewGroup, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int i) {
        FAQ faq = list.get(i);
        holder.tvAnswer.setText(faq.getAnswer());
        holder.tvQuestion.setText(faq.getQuestion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FAQViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvQuestion)
        TextView tvQuestion;

        @BindView(R.id.tvAnswer)
        TextView tvAnswer;

        FAQViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
