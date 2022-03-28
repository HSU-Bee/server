package hsu.bee.petra.attraction.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_type_id")
	private ContentType contentType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_category_id")
	private MainCategory mainCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "middle_category_id")
	private MiddleCategory middleCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

}
